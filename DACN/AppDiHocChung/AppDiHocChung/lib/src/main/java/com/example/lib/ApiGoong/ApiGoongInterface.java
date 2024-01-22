package com.example.lib.ApiGoong;




import com.example.lib.ApiGoong.Model.DirectionResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiGoongInterface {
    @GET("Direction")
    Call<DirectionResponses> getDirections(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("api_key") String api_key,
            @Query("vehicle") String vehicle
    );
}
