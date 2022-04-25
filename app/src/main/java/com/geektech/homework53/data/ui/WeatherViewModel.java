package com.geektech.homework53.data.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.homework53.App;
import com.geektech.homework53.common.Resource;
import com.geektech.homework53.data.model.MainResponse;
import com.geektech.homework53.data.model.WeatherApp;

public class WeatherViewModel extends ViewModel {
    public LiveData<Resource<WeatherApp>> liveData;

    public WeatherViewModel() {
    }


    public void getWeatherByCityName(String cityName) {
        liveData = App.repository.getWeatherByCityName(cityName);
    }
}
