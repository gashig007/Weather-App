package com.geektech.homework53.domain.repository;

import androidx.lifecycle.MutableLiveData;

import com.geektech.homework53.common.Resource;
import com.geektech.homework53.data.model.MainResponse;
import com.geektech.homework53.data.model.Weather;

public interface MainRepository {
    MutableLiveData<Resource<MainResponse>> getWeathers();

    MutableLiveData<Resource<Weather>> getWeatherById(Integer id);
}
