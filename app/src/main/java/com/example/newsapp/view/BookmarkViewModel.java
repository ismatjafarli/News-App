package com.example.newsapp.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsapp.data.NewsRepository;
import com.example.newsapp.model.News;

import java.util.List;

public class BookmarkViewModel extends AndroidViewModel {
    public static NewsRepository repository;
    public final LiveData<List<News>> allNews;


    public BookmarkViewModel(@NonNull Application application) {
        super(application);
        repository = new NewsRepository(application);
        allNews = repository.getAllData();
    }

    public LiveData<List<News>> getAllNews() {return allNews; }
    public void insert(News news) {repository.insert(news);}
    public LiveData<News> get(int id) {return repository.get(id);}
    public void delete(News news) {repository.delete(news);}
    public void deleteAll() {repository.deleteAll();}


}
