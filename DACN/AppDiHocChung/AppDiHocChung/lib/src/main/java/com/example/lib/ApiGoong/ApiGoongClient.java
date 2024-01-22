package com.example.lib.ApiGoong;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiGoongClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://rsapi.goong.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
