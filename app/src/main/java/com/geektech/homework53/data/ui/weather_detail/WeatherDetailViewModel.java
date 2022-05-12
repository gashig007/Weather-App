package com.geektech.homework53.data.ui.weather_detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.homework53.common.Resource;
import com.geektech.homework53.data.model.Weather;
import com.geektech.homework53.data.model.WeatherApp;
import com.geektech.homework53.data.remote.WeatherApi;
import com.geektech.homework53.data.repositories.MainRepositoryImpl;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherDetailViewModel extends ViewModel {
    private MainRepositoryImpl repository;
    public LiveData<Resource<WeatherApp>> liveData;

    @Inject
    public WeatherDetailViewModel(MainRepositoryImpl repository) {
        this.repository = repository;
    }

    public void getWeatherByMap(String latitude, String longitude) {
        liveData = repository.getWeatherByMap(latitude, longitude);
    }
}
