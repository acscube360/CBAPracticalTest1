package com.example.arunawijesinghe.constants;
import com.example.arunawijesinghe.model.LoginResponse;
import com.example.arunawijesinghe.model.User;
import com.example.arunawijesinghe.model.UserAuthentication;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.POST;

public interface RetrofitApi {
    String BASE_URL = "http://123.231.9.43:3999";

    @POST("/")
    Call<LoginResponse> login(@Body UserAuthentication authentication);
}
