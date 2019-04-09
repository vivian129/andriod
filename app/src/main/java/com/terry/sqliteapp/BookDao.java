package com.terry.sqliteapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM books")
  List< Book> getbooks();

 @Insert

   void  insert (Book k);
 @Update
 void update (Book x);
 @Delete
 void  delete(Book y);
 @Query("SELECT *FROM books WHERE author=:name")
     List<Book> fetchbyAuthor(String name);

}
