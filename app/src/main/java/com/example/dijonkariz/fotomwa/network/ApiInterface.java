package com.example.dijonkariz.fotomwa.network;

import com.example.dijonkariz.fotomwa.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("inbox.json")
    Call<List<Message>> getInbox();
}
