package com.example.newsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.adapter.BookmarkAdapter;
import com.example.newsapp.databinding.ActivityBookmarkBinding;
import com.example.newsapp.model.News;
import com.example.newsapp.view.BookmarkViewModel;

import java.util.List;

public class BookmarkActivity extends AppCompatActivity implements BookmarkAdapter.OnItemClickListener{
    private BookmarkViewModel newsViewModel;
    private ActivityBookmarkBinding binding;
    private BookmarkAdapter bookmarkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bookmark);
        setBackButton();

        LinearLayoutManager manager =
                new LinearLayoutManager(BookmarkActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewBookmark.setHasFixedSize(true);
        binding.recyclerViewBookmark.setLayoutManager(manager);

        newsViewModel = ViewModelProviders.of(this).get(BookmarkViewModel.class);
        newsViewModel.getAllNews().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                bookmarkAdapter = new BookmarkAdapter(BookmarkActivity.this, news, BookmarkActivity.this);
                binding.recyclerViewBookmark.setAdapter(bookmarkAdapter);
//                Log.d("Testing", "onChangedROOMTiitle: " + news.get(0).getTitle());
//                Log.d("Testing", "onChangedROOMUrl: " +news.get(0).getUrl());
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                newsViewModel.delete(bookmarkAdapter.getNewsAt(viewHolder.getAdapterPosition()));
                Toast.makeText(BookmarkActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.recyclerViewBookmark);

    }

    private void setBackButton() {
        setSupportActionBar(binding.toolbarBookmarks);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu_my_news, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all:
                deleteAll();
                Toast.makeText(this, "No bookmarks", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void deleteAll() {
        newsViewModel.deleteAll();
    }

    @Override
    public void onItemClick(int position) {
        News news = newsViewModel.getAllNews().getValue().get(position);

        Intent intent = new Intent(BookmarkActivity.this, WebActivity.class);
        intent.putExtra("url", news.getUrl());
        startActivity(intent);
    }
}