package com.example.newsapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.newsapp.model.News;

import java.util.List;

@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(News news);

    @Query("DELETE FROM news_table")
    void deleteAll();

    @Query("SELECT * FROM news_table")
    LiveData<List<News>> getAllNews();

    @Query("SELECT * FROM news_table WHERE news_table.id == :id")
    LiveData<News> get(int id);

    @Delete
    void delete(News news);

}
