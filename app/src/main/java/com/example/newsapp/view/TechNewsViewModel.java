package com.example.newsapp.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.model.Root;

public class TechNewsViewModel extends AndroidViewModel {
    TechNewsRepository repository;

    public TechNewsViewModel(@NonNull Application application) {
        super(application);
        repository = new TechNewsRepository(application);
    }

    public MutableLiveData<Root> getTechNews () {
        return repository.getTechNews();
    }
}
