package com.example.newsapp.fragmnet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.adapter.ArticlesAdapter;
import com.example.newsapp.databinding.FragmentArticlesBinding;
import com.example.newsapp.model.Root;
import com.example.newsapp.view.ArticlesViewModel;

public class ArticlesFragment extends Fragment{
    ArticlesViewModel articlesViewModel;
    FragmentArticlesBinding binding;

    public ArticlesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_articles, container,false);
        View view = binding.getRoot();

        getArticles();

        return view;
    }

    public void getArticles() {
        RecyclerView.LayoutManager manager =
                new GridLayoutManager(getContext(),2);
        binding.recyclerViewArticles.setLayoutManager(manager);

        articlesViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);
        articlesViewModel.getArticles().observe(this, new Observer<Root>() {
            @Override
            public void onChanged(Root root) {
                ArticlesAdapter articlesAdapter = new ArticlesAdapter(getContext(), root.getArticles());
                binding.recyclerViewArticles.setAdapter(articlesAdapter);
            }
        });


    }

}
