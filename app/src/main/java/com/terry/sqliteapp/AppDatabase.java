package com.terry.sqliteapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database( entities = {Book.class},version = 1)
public abstract class AppDatabase  extends RoomDatabase {

    public abstract BookDao getDao();
    //private Context context;
    private  static AppDatabase appDatabase;
    public static  synchronized  AppDatabase getInstance( Context x){

      if (appDatabase==null) {
        appDatabase= Room.databaseBuilder( x,AppDatabase.class,"School_db")
                .allowMainThreadQueries()
                .build();

      }
      return appDatabase;
    }
}


