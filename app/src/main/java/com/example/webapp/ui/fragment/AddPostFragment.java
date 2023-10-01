package com.example.webapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.webapp.R;


public class AddPostFragment extends Fragment {


    private ImageView add;
    private CustomDialog_add_data dialog;

    public AddPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);

        add = view.findViewById(R.id.add_data);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new CustomDialog_add_data();
                dialog.show(getFragmentManager(), "CustomDialog_add_data");

            }
        });

        return view;
    }
}