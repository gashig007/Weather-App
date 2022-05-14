package com.geektech.homework53.data.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.homework53.common.Resource;
import com.geektech.homework53.data.model.WeatherApp;
import com.geektech.homework53.data.repositories.MainRepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {
    public LiveData<Resource<WeatherApp>> liveData;
    private MainRepositoryImpl repository;

    public WeatherViewModel() {
    }

    @Inject
    public WeatherViewModel(MainRepositoryImpl repository) {
        this.repository = repository;
    }

    public void getWeatherByMap(String latitude, String longitude) {
        liveData = repository.getWeatherByMap(latitude, longitude);
    }

    public List<WeatherApp> getWeatherRoom() {
        return repository.getWeather();
    }
}
