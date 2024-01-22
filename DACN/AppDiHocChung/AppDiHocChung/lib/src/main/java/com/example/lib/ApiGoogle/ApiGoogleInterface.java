package com.example.lib.ApiGoogle;

import com.example.lib.ApiGoogle.Model.DirectionResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiGoogleInterface {
    @GET("directions/json")
    Call<DirectionResponses> getDirections(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("key") String key
    );
}
