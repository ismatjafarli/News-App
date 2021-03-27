package com.example.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.activity.BookmarkActivity;
import com.example.newsapp.databinding.ActivityBookmarkBinding;
import com.example.newsapp.databinding.TitleCardBinding;
import com.example.newsapp.model.News;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {
    private Context context;
    private List<News> newsList;
    private OnItemClickListener listener;

    public BookmarkAdapter(Context context, List<News> newsList, OnItemClickListener listener) {
        this.context = context;
        this.newsList = newsList;
        this.listener = listener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TitleCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.title_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.activityBookmarkBinding.setNews(news);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClickListener onContactClickListener;
        TitleCardBinding activityBookmarkBinding;

        public ViewHolder(@NonNull TitleCardBinding binding, OnItemClickListener listener) {
            super(binding.getRoot());
            activityBookmarkBinding = binding;

            this.onContactClickListener = listener;
            activityBookmarkBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContactClickListener.onItemClick(getAdapterPosition());
        }
    }

}
