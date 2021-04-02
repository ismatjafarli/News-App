package com.example.newsapp.fragmnet;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newsapp.R;
import com.example.newsapp.adapter.BusinessNewsAdapter;
import com.example.newsapp.databinding.FragmentBusinessNewsBinding;
import com.example.newsapp.model.Root;
import com.example.newsapp.view.BusinessNewsViewModel;

public class BusinessNewsFragment extends Fragment {
    BusinessNewsViewModel viewModel;
    FragmentBusinessNewsBinding binding;

    public BusinessNewsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_business_news, container, false);
        View view = binding.getRoot();
        getBusinessNews();

        return view;
    }

    public void getBusinessNews() {
        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewBusiness.setHasFixedSize(true);
        binding.recyclerViewBusiness.setLayoutManager(manager);

        viewModel = ViewModelProviders.of(this).get(BusinessNewsViewModel.class);
        viewModel.getBusinessNews().observe(this, new Observer<Root>() {
            @Override
            public void onChanged(Root root) {
                Log.d("Testing", "onChanged: " + root.getStatus());
                BusinessNewsAdapter businessNewsAdapter = new BusinessNewsAdapter(root.getArticles(), getContext());
                binding.recyclerViewBusiness.setAdapter(businessNewsAdapter);
                binding.progressMain.setVisibility(View.INVISIBLE);

            }
        });
    }

}
