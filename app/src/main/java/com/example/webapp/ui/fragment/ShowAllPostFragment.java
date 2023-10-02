package com.example.webapp.ui.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.webapp.R;
import com.example.webapp.data.api.Client;
import com.example.webapp.data.api.Iclient;
import com.example.webapp.data.model.retrofit.Post;
import com.example.webapp.databinding.FragmentShowAllPostBinding;
import com.example.webapp.ui.adapter.ItemPostAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllPostFragment extends Fragment {

    FragmentShowAllPostBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShowAllPostBinding.inflate(inflater,container,false);

        getData();

        return binding.getRoot();
    }

    private void getData() {
        Client.getClient().create(Iclient.class).getAllPost().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                setUpRv(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    private void setUpRv(List<Post> body) {
        ArrayList<com.example.webapp.ui.viewModel.Post> posts = new ArrayList<>();
        if (body!=null) {
            for (Post post : body) {
                posts.add(new com.example.webapp.ui.viewModel.Post(post));
            }
        }
        binding.rvAll.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rvAll.setAdapter(new ItemPostAdapter(posts,getContext()));
    }
}