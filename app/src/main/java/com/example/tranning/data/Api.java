package com.example.tranning.data;

import com.example.tranning.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://simplifiedcoding.net/demos/";
    @GET("marvel")
    Call<List<User>> getsuperHeroes();
}