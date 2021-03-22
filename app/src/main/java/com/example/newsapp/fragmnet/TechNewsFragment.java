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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newsapp.R;
import com.example.newsapp.adapter.TechNewsAdapter;
import com.example.newsapp.databinding.FragmentTechNewsBinding;
import com.example.newsapp.model.Root;
import com.example.newsapp.view.TechNewsViewModel;

public class TechNewsFragment extends Fragment {
    TechNewsViewModel viewModel;
    FragmentTechNewsBinding binding;

    public TechNewsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tech_news, container, false);
        View view = binding.getRoot();

        getTechNews();

        return view;
    }

    public void getTechNews () {

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewTech.setHasFixedSize(true);
        binding.recyclerViewTech.setLayoutManager(manager);

        viewModel = ViewModelProviders.of(this).get(TechNewsViewModel.class);
        viewModel.getTechNews().observe(this, new Observer<Root>() {
            @Override
            public void onChanged(Root root) {
                TechNewsAdapter techNewsAdapter = new TechNewsAdapter(getContext(), root.getArticles());
                binding.recyclerViewTech.setAdapter(techNewsAdapter);
            }
        });
    }
}
