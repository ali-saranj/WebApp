package com.example.webapp.data.api;

import com.example.webapp.data.model.retrofit.Post;
import com.example.webapp.ui.viewModel.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Iclient {
    @GET("Weblog/Select_Weblog.php")
    Call<List<Post>> getAllPost();


    //for_Register
    @FormUrlEncoded
    @POST("Regester_user.php")
    Call<User> Register (@Field("username") String username,
                         @Field("password") String password);

    //for_login
    @FormUrlEncoded
    @POST("Login_user.php")
    Call<Post> login (@Field("username") String username,
                         @Field("password") String password);


    //for_Add_data
    @FormUrlEncoded
    @POST("Insert_data.php")
    Call<Post> Insert_data (@Field("username") String username,
                            @Field("title") String title,
                            @Field("note")String note);

}
