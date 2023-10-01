package com.example.webapp.data.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.webapp.ui.viewModel.User;

public class SharedpreferencesUser {
    Context context;
    SharedPreferences sharedPreferences;

    public SharedpreferencesUser(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Check_user",Context.MODE_PRIVATE);
    }

    public void addUser(User user){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",user.getUsername());
        editor.putString("password",user.getPassword());
        editor.apply();
    }

    public boolean CheckUserIsExist(){
        return !sharedPreferences.getString("username", "").equals("");
    }

    public User getUser(){
        if (CheckUserIsExist()) {
            User user = new User(0,sharedPreferences.getString("username",""),sharedPreferences.getString("password",""));
            return user;
        }else {
            return null;
        }
    }


}
