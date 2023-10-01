package com.example.webapp.ui.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.webapp.data.sharedpreferences.SharedpreferencesUser;
import com.example.webapp.ui.activity.MainActivity;
import com.example.webapp.ui.viewModel.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private EditText username_login,password_login;
    private Button btn_login;

    SharedpreferencesUser sharedpreferencesUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fr_sign_in, container, false);

        username_login = view.findViewById(R.id.username_login);
        password_login = view.findViewById(R.id.Password_login);
        btn_login = view.findViewById(R.id.SignIn);

        sharedpreferencesUser = new SharedpreferencesUser(getContext());

        checkuserlogin();

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
        Call<Model_login> call = client.login(username_login.getText().toString(),password_login.getText().toString());
        call.enqueue(new Callback<Model_login>() {
            @Override
            public void onResponse(Call<Model_login> call, Response<Model_login> response) {
                Model_login obj = response.body();
                String output = obj.getMessage();
                if (output.equals("ok")){
                    Toast.makeText(getContext(), "با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                    sharedpreferencesUser.addUser(new User(0,username_login.getText().toString(),password_login.getText().toString()));
                    checkuserlogin();
                }
                if (output.equals("error")){
                    Toast.makeText(getContext(), "همچین کاربری وجود ندارد لطفا ثبت نام کنید", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model_login> call, Throwable t) {
                Toast.makeText(getContext(), "مشکل برقراری ارتباط", Toast.LENGTH_SHORT).show();
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


    private void checkuserlogin() {
        if (sharedpreferencesUser.CheckUserIsExist()){
            getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
        }

    }

}