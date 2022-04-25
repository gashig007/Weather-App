package com.geektech.homework53.data.remote;

import com.geektech.homework53.data.model.MainResponse;
import com.geektech.homework53.data.model.WeatherApp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("data/2.5/weather")
    Call<WeatherApp> getTemp(
            @Query("q") String city,
            @Query("appid") String apiKey
    );
}
