package com.example.newsapp.view;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.api.ApiService;
import com.example.newsapp.api.RetrofitClientInstance;
import com.example.newsapp.model.Root;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessNewsRepository {
    private MutableLiveData<Root> mutableLiveData = new MutableLiveData<>();
    private Application application;
    private static final String TAG = "Testing";

    public BusinessNewsRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<Root> getBusinessNews() {
        ApiService apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);

        Call<Root> call = apiService.getBusinessNews("us", "business", "40e0870ac7b24875916175437e36ad1e");
        call.enqueue(new Callback<Root>() {

            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Log.d(TAG, "onResponse: "+response.body());
                if (!response.isSuccessful()) {
                    return;
                }
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }

        });
        return mutableLiveData;
    }
}
