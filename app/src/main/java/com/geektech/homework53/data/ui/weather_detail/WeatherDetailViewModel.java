package com.geektech.homework53.data.ui.weather_detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.homework53.common.Resource;
import com.geektech.homework53.data.model.Weather;
import com.geektech.homework53.data.repositories.MainRepositoryImpl;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherDetailViewModel extends ViewModel {
    private MainRepositoryImpl mainRepository;
    public LiveData<Resource<Weather>> liveData;

    @Inject
    public WeatherDetailViewModel(MainRepositoryImpl mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void getWeatherById(Integer id){
        liveData = mainRepository.getWeatherById(id);
    }
}
