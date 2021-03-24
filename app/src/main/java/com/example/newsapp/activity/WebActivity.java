package com.example.newsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityWebBinding;

public class WebActivity extends AppCompatActivity {
    ActivityWebBinding binding;
    String url;
    String check = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web);
        setSupportActionBar(binding.toolbarWebView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        check = intent.getStringExtra("check");


        binding.activityWeb.setWebChromeClient( new MyWebChromeClient());
        binding.activityWeb.setWebViewClient( new webClient());
        binding.activityWeb.getSettings().setLoadWithOverviewMode(true);
        binding.activityWeb.getSettings().setSupportZoom(true);
        binding.activityWeb.getSettings().setJavaScriptEnabled(true);
        binding.activityWeb.loadUrl(url);

        if(check.equals("fromArticles")) {
            binding.textViewToolbar.setText("Articles");
        } else if(check.equals("fromBusinessNews")) {
            binding.textViewToolbar.setText("Business News");
        } else{
            binding.textViewToolbar.setText("Tech News");
        }



    }

    public class MyWebChromeClient extends WebChromeClient {
        public void onProgressChanged(WebView view, int newProgress) {
            binding.progressWebView.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            binding.progressWebView.setVisibility(View.VISIBLE);
            binding.progressWebView.setProgress(newProgress);
        }
    }

    public class webClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            binding.progressWebView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        if (   binding.activityWeb.canGoBack()) {
            binding.activityWeb.goBack();
            binding.activityWeb.setVisibility(View.GONE);
        } else  {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_item:
                shareText();
                return true;
            case R.id.add_my_news_item:
                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                 this.finish();
                 return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void shareText() {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBodyText = url;
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject/Title");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(intent, "Share with"));
    }
}
