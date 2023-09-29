package com.example.webapp.data.api;

import com.example.webapp.data.model.retrofit.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Iclient {
    @GET("Weblog/Select_Weblog.php")
    Call<Response> getAllPost();
}
