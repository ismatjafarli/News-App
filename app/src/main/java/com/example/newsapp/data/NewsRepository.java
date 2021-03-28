package com.example.newsapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.newsapp.model.News;
import com.example.newsapp.util.NewsRoomDatabase;

import java.util.List;

public class NewsRepository {
    private NewsDao newsDao;
    private LiveData<List<News>> allNews;

    public NewsRepository(Application application) {
        NewsRoomDatabase db = NewsRoomDatabase.getDatabase(application);
        newsDao = db.newsDao();
        allNews = newsDao.getAllNews();
    }

    public LiveData<List<News>> getAllData() { return allNews; }

    public void insert(News news) {
        NewsRoomDatabase.databaseWriteExecutor.execute(() ->{
            newsDao.insert(news);
        });
    }

    public LiveData<News> get(int id) {
        return newsDao.get(id);
    }

    public void deleteAll() {
        NewsRoomDatabase.databaseWriteExecutor.execute(() -> newsDao.deleteAll());
    }

    public void delete(News news) {
        NewsRoomDatabase.databaseWriteExecutor.execute(() -> newsDao.delete(news));
    }
}
