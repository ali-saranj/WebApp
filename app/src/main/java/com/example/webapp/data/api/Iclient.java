package com.example.webapp.data.api;

import com.example.webapp.data.model.retrofit.Model_login;
import com.example.webapp.data.model.retrofit.Post;
import com.example.webapp.ui.viewModel.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Iclient {
    @GET("Select_Weblog.php")
    Call<List<Post>> getAllPost();


    //for_Register
    @FormUrlEncoded
    @POST("Regester_user.php")
    Call<Model_login> Register(@Field("username") String username,
                               @Field("password") String password);

    //for_login
    @FormUrlEncoded
    @POST("Login_user.php")
    Call<Model_login> login(@Field("username") String username,
                            @Field("password") String password);


    //for_Add_data
    @FormUrlEncoded
    @POST("Insert_weblog.php")
    Call<com.example.webapp.ui.viewModel.Post> Insert_data(
            @Field("title") String title,
            @Field("des") String note,
            @Field("username") String username
            ,@Field("image") String image
    );
}
