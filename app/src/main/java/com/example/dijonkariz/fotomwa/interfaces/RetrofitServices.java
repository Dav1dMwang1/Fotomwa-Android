package com.example.dijonkariz.fotomwa.interfaces;

import com.example.dijonkariz.fotomwa.API_connect.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitServices {

    @FormUrlEncoded
    @POST("auth/auth")
    Call<LoginResponse> isValidUser(
            @Field("email") String email,
            @Field("password") String password);
}
