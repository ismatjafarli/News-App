package com.example.newsapp.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.model.Root;

import java.util.List;

public class BusinessNewsViewModel extends AndroidViewModel {
    BusinessNewsRepository repository;

    public BusinessNewsViewModel(@NonNull Application application) {
        super(application);
        repository = new BusinessNewsRepository(application);
    }

    public MutableLiveData<Root> getBusinessNews() {
        return repository.getBusinessNews();
    }

}
