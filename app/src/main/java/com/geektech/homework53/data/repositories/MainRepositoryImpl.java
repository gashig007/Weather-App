package com.geektech.homework53.data.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.geektech.homework53.common.Resource;
import com.geektech.homework53.data.local.WeatherDao;
import com.geektech.homework53.data.model.MainResponse;
import com.geektech.homework53.data.model.Weather;
import com.geektech.homework53.data.model.WeatherApp;
import com.geektech.homework53.data.remote.WeatherApi;
import com.geektech.homework53.domain.repository.MainRepository;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepositoryImpl implements MainRepository {
    private WeatherApi api;
    private WeatherDao dao;

    @Inject
    public MainRepositoryImpl(WeatherApi api, WeatherDao dao) {
        this.api = api;
        this.dao = dao;
    }

    public MutableLiveData<Resource<WeatherApp>> getWeatherByMap(String lat, String lot) {
        MutableLiveData<Resource<WeatherApp>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getTemp(lat, lot, "bf77ac8444ca487f6bad28e7bbf1abd7").enqueue(new Callback<WeatherApp>() {
            @Override
            public void onResponse(Call<WeatherApp> call, Response<WeatherApp> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                    dao.insert(response.body());
                } else {
                    liveData.setValue(Resource.error(response.message(), null));
                    Log.e("ERROR IS: ", response.message());

                }
            }

            @Override
            public void onFailure(Call<WeatherApp> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });

        return liveData;
    }

//    @Override
//    public MutableLiveData<Resource<MainResponse>> getWeathers() {
//        return null;
//    }

//    @Override
//    public MutableLiveData<Resource<Weather>> getWeatherById(Integer id) {
//        MutableLiveData<Resource<Weather>> liveData = new MutableLiveData<>();
//        liveData.setValue(Resource.loading());
//        api.getWeatherById(id).enqueue(new Callback<Weather>() {
//            @Override
//            public void onResponse(Call<Weather> call, Response<Weather> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    liveData.setValue(Resource.success(response.body()));
//                } else {
//                    liveData.setValue(Resource.error(response.message(), null));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Weather> call, Throwable t) {
//                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
//            }
//        });
//        return liveData;
//    }
}
