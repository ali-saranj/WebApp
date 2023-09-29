package com.example.webapp.ui.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.webapp.R;
import com.example.webapp.data.api.Client;
import com.example.webapp.data.api.Iclient;
import com.example.webapp.data.model.retrofit.Post;
import com.example.webapp.databinding.FragmentShowAllPostBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllPostFragment extends Fragment {

    FragmentShowAllPostBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_all_post,container,false);



        return binding.getRoot();
    }
}