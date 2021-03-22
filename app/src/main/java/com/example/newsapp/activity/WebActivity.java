package com.example.newsapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityWebBinding;

public class WebActivity extends AppCompatActivity {
    ActivityWebBinding binding;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        binding.activityWeb.loadUrl(url);

    }
}