package com.example.newsapp.api;

import com.example.newsapp.model.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("top-headlines")
    Call<Root> getBusinessNews(@Query("country") String country,
                               @Query("category") String business,
                               @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<Root> getTechCrunchNews(@Query("sources") String sources,
                                 @Query("apiKey") String apiKey);

    @GET("everything")
    Call<Root> getArticles(@Query("domains") String domains,
                           @Query("apiKey") String apiKey);

}
