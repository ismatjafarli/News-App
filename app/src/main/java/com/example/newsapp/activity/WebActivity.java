package com.example.newsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

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
import com.example.newsapp.adapter.BookmarkAdapter;
import com.example.newsapp.databinding.ActivityWebBinding;
import com.example.newsapp.model.News;
import com.example.newsapp.view.BookmarkViewModel;

public class WebActivity extends AppCompatActivity {
    private ActivityWebBinding binding;
    private String url;
    private String check = "News";
    private String title = null;
    private BookmarkViewModel newsViewModel;
    private BookmarkAdapter bookmarkAdapter = new BookmarkAdapter();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web);
        intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");

        binding.activityWeb.setWebChromeClient( new MyWebChromeClient());
        binding.activityWeb.setWebViewClient( new WebClient());
        binding.activityWeb.getSettings().setLoadWithOverviewMode(true);
        binding.activityWeb.getSettings().setSupportZoom(true);
        binding.activityWeb.getSettings().setJavaScriptEnabled(true);
        binding.activityWeb.loadUrl(url);

        newsViewModel = ViewModelProviders.of(this).get(BookmarkViewModel.class);

        setBackButton();
        setToolbarTitle();

    }

    private void setToolbarTitle() {
        check = intent.getStringExtra("check");

        if(check == null){
            binding.textViewToolbar.setText("My news/articles");
        } else {
            if (check.equals("fromArticles")) {
                binding.textViewToolbar.setText("Articles");
            } else if (check.equals("fromBusinessNews")) {
                binding.textViewToolbar.setText("Business News");
            } else {
                binding.textViewToolbar.setText("Tech News");
            }
        }
    }

    private void setBackButton() {
        setSupportActionBar(binding.toolbarWebView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public class MyWebChromeClient extends WebChromeClient {
        public void onProgressChanged(WebView view, int newProgress) {
            binding.progressWebView.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            binding.progressWebView.setVisibility(View.VISIBLE);
            binding.progressWebView.setProgress(newProgress);
        }
    }

    public class WebClient extends WebViewClient {
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
                addToMyList();
                return true;
            case android.R.id.home:
                 this.finish();
                 return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareText() {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBodyText = url;
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject/Title");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(intent, "Share with"));
    }

    private void addToMyList() {
//        List<News> newsList = bookmarkAdapter.getAllNews();
//        int flag = 0;
//
//        for(int i = 0; i <= newsList.size(); i ++) {
//            if(newsList.get(i).getUrl().equals(url)){
//                flag = 1;
//            }
//        }
//
//        if(flag == 1) {
//            Toast.makeText(this, "same", Toast.LENGTH_SHORT).show();
//        }else {
            News newNews = new News(title, url);
            newsViewModel.insert(newNews);
            Toast.makeText(WebActivity.this, "Bookmarked!", Toast.LENGTH_SHORT).show();
//        }

    }

}


