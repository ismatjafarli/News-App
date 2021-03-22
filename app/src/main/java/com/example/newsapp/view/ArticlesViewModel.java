package com.example.newsapp.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.model.Root;

public class ArticlesViewModel extends AndroidViewModel {
    ArticlesRepository repository;

    public ArticlesViewModel(@NonNull Application application) {
        super(application);
        repository = new ArticlesRepository(application);
    }

    public MutableLiveData<Root> getArticles() {
        return repository.getArticles();
    }

}
