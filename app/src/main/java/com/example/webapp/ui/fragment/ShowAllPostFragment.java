package com.example.webapp.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.webapp.R;
import com.example.webapp.data.api.Client;
import com.example.webapp.data.api.Iclient;
import com.example.webapp.data.model.retrofit.Post;
import com.example.webapp.data.sharedpreferences.SharedpreferencesUser;
import com.example.webapp.databinding.FragmentShowAllPostBinding;
import com.example.webapp.ui.adapter.ItemPostAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllPostFragment extends Fragment {

    FragmentShowAllPostBinding binding;
    private SwipeRefreshLayout swipeRefreshLayout;
    List<com.example.webapp.ui.viewModel.Post> modelProducts;
    private SearchView searchView;

    private SharedpreferencesUser sharedpreferencesUser;
    private String username;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShowAllPostBinding.inflate(inflater, container, false);

        binding.SwipeRefresh.findViewById(R.id.Swipe_Refresh);
        swipeRefreshLayout = binding.SwipeRefresh;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onRefresh() {
                getData();

                //Ali

                //adaptor.notifyDataSetChanged();


                swipeRefreshLayout.setRefreshing(false);
            }
        });


        //getUsernameForShow
        username = sharedpreferencesUser.getUser().getUsername();


        //SearchView
        searchView = binding.searchView.findViewById(R.id.search_view);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                search_view(newText);

                return true;
            }
        });


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
                Toast.makeText(getContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpRv(List<Post> body) {
        ArrayList<com.example.webapp.ui.viewModel.Post> posts = new ArrayList<>();
        if (body != null) {
            for (Post post : body) {
                posts.add(new com.example.webapp.ui.viewModel.Post(post));
            }
        }
        binding.rvAll.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvAll.setAdapter(new ItemPostAdapter(posts, getContext()));


    }


    private void search_view(String text) {
        List<com.example.webapp.ui.viewModel.Post> filter_list = new ArrayList<>();

        for (com.example.webapp.ui.viewModel.Post item : modelProducts) {

            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filter_list.add(item);
            }

        }

        if (filter_list.isEmpty()) {
            Toast.makeText(getContext(), "this not found", Toast.LENGTH_SHORT).show();
        } else {

            //Ali

//            ItemPostAdapter.setData(filter_list);


        }

    }


}