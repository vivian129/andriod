package com.terry.sqliteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    BookDao db;
    String TAG = "SQLITE";

    @BindView(R.id.InputTitle)
    EditText InputTittle;
    @BindView(R.id.InputAuthor)
    EditText InputAuthor;
    @BindView(R.id.InputIsbn)
    EditText InputIsbn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
ButterKnife.bind(this);
        db = AppDatabase.getInstance(this).getDao();

    }

    public void delete(View view) {
        try{
        List<Book> books = db.getbooks();
        Book first = books.get(0);
        if (first != null) {
            db.delete(first);
        }
        } catch (Exception x){

            Toast.makeText(this, "error while deleting", Toast.LENGTH_SHORT).show();


        }

    }


    public void save(View view) {
String title =InputTittle.getText().toString().trim();
String author =InputAuthor.getText().toString().trim();
String isbn =InputIsbn.getText().toString().trim();
if (title.isEmpty()||author.isEmpty()||isbn.isEmpty()){
    Toast.makeText(this, "Fill all the values", Toast.LENGTH_SHORT).show();
    return;

}


        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setIsbn(isbn);
        db.insert(book);
        InputIsbn.setText("");
        InputAuthor.setText("");
        InputTittle.setText("");


    }

    public void fetch(View view) {
        Intent x=new Intent(this,fetch.class);
        List<Book> books = db.getbooks();
        for (Book b : books) {
            Log.d(TAG, "fetch: " + b.getTitle());
            Log.d(TAG, "fetch: " + b.getAuthor());
            Log.d(TAG, "fetch: " + b.getIsbn());
            Log.d(TAG, "fetch: " + b.getId());
            Toast.makeText(this, "fetched", Toast.LENGTH_SHORT).show();


        }
    }

    public void insert(View view) {
        Book book = new Book();
        book.setAuthor("Ngugi wa Thingo");
        book.setTitle("The River Between");
        book.setIsbn("123456");
        db.insert(book);
        Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();

    }

    public void update(View view) {
        try {


            List<Book> books = db.getbooks();
            Book first = books.get(0);
            if (first != null) {
                first.setIsbn("123456");
                first.setAuthor("jemima thiongo");
                db.update(first);

            }
        } catch ( Exception x){
            Toast.makeText(this, "error while updating", Toast.LENGTH_SHORT).show();


        }
        Toast.makeText(this, "update", Toast.LENGTH_SHORT).show();
    }
}

