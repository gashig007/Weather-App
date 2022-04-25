package com.geektech.homework53.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.geektech.homework53.common.Resource;
import com.geektech.homework53.data.model.WeatherApp;
import com.geektech.homework53.data.remote.WeatherApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private WeatherApi api;

    public MainRepository(WeatherApi api) {
        this.api = api;
    }

    public MutableLiveData<Resource<WeatherApp>> getWeatherByCityName(String cityName) {
        MutableLiveData<Resource<WeatherApp>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getTemp(cityName, "bf77ac8444ca487f6bad28e7bbf1abd7").enqueue(new Callback<WeatherApp>() {
            @Override
            public void onResponse(Call<WeatherApp> call, Response<WeatherApp> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                } else {
                    liveData.setValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<WeatherApp> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });

        return liveData;
    }

}
