package com.terry.sqliteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class fetch extends AppCompatActivity {
    @BindView(R.id.listbooks)
    ListView listView;

BookDao db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);
        ButterKnife.bind(this);
        db=AppDatabase.getInstance(this).getDao();
        final List<Book>data=db.getbooks();
        BaseAdapter adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Book x =data.get(position);
                if (convertView==null){
                  convertView= LayoutInflater.from(getApplicationContext()) .inflate(R.layout.list_item,null) ;

                }

                TextView txttitle=convertView.findViewById(R.id.textviewTitle);
                TextView txtAuthor=convertView.findViewById(R.id.textviewAuthor);
                TextView txtIsbn=convertView.findViewById(R.id.textviewIsbn);
                txttitle.setText(x.getTitle());
                txtAuthor.setText(x.getAuthor());
                txtIsbn.setText(x.getIsbn());
                return convertView;
            }
        };
        listView.setAdapter(adapter);
    }
}
