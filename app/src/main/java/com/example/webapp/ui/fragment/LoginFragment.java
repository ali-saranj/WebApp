package com.example.webapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webapp.R;
import com.example.webapp.data.api.Client;
import com.example.webapp.data.api.Iclient;
import com.example.webapp.data.model.retrofit.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private EditText username_login,password_login;
    private Button btn_login;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fr_sign_in, container, false);

        username_login = view.findViewById(R.id.username_login);
        password_login = view.findViewById(R.id.Password_login);
        btn_login = view.findViewById(R.id.SignIn);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInputs()){
                    login_user();
                }
            }
        });


        return view;
    }


    private void login_user() {
        Iclient client = Client.getClient().create(Iclient.class);

        Call<Post> call = client.login(username_login.getText().toString(),username_login.getText().toString());
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(getContext(), "this is ok", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean validateInputs(){
        boolean isValid = true;

        if (username_login.getText().toString().trim().isEmpty()){
            username_login.setError("نام کاربری نمی تواند خالی باشد!");
            isValid = false;
        }

        if (username_login.getText().toString().trim().isEmpty()){
            username_login.setError("رمز شما خالی است !");
            isValid = false;
        }
        return isValid;
    }
}