package com.geektech.homework53.domain.repository;

import androidx.lifecycle.MutableLiveData;

import com.geektech.homework53.common.Resource;
import com.geektech.homework53.data.model.MainResponse;
import com.geektech.homework53.data.model.Weather;
import com.geektech.homework53.data.model.WeatherApp;

public interface MainRepository {
    MutableLiveData<Resource<WeatherApp>> getWeatherByMap(String latitude, String longitude);
}
