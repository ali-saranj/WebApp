package com.example.webapp.ui.fragment;

import android.content.Intent;
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
import com.example.webapp.data.model.retrofit.Model_login;
import com.example.webapp.data.model.retrofit.Post;
import com.example.webapp.ui.activity.MainActivity;
import com.example.webapp.ui.viewModel.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RegisterFragment extends Fragment {


    private EditText username,password;
    private Button btn_Register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fr_sign_up, container, false);

        username = view.findViewById(R.id.username_Register);
        password = view.findViewById(R.id.password_Register);
        btn_Register = view.findViewById(R.id.CreateAccount);



        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInputs()){
                    create_account();
                }

            }
        });

        return view;
    }


    private void create_account() {
        Iclient client = Client.getClient().create(Iclient.class);

        Call<Model_login> call = client.Register(username.getText().toString(),password.getText().toString());
        call.enqueue(new Callback<Model_login>() {
            @Override
            public void onResponse(Call<Model_login> call, Response<Model_login> response) {
                Model_login obj = response.body();
                String output = obj.getMessage();
                if (output.equals("ok")) {
                    Toast.makeText(getContext(), "با موفقیت انجام شد", Toast.LENGTH_SHORT).show();

                    getActivity().startActivity(new Intent(getActivity(), MainActivity.class));

                }
                if (output.equals("no")){
                    Toast.makeText(getContext(), "همچین کاربری موجود است لطفا از نام کاربری دیگری استفاده کنید!", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<Model_login> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean validateInputs(){
        boolean isValid = true;

        if (username.getText().toString().trim().isEmpty()){
            username.setError("نام کاربری نمی تواند خالی باشد!");
            isValid = false;
        }

        if (password.getText().toString().trim().isEmpty()){
            username.setError("رمز شما خالی است !");
            isValid = false;
        }
        return isValid;
    }



}
