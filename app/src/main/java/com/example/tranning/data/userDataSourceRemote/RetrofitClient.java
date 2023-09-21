package com.example.tranning.data.userDataSourceRemote;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private Api myApi;

    @Inject
    public RetrofitClient(Api api) {
        this.myApi = api;
    }

    public Api getMyApi() {
        return myApi;
    }
}
