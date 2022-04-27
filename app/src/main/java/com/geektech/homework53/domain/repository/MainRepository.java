package com.geektech.homework53.domain.repository;

import androidx.lifecycle.MutableLiveData;

import com.geektech.homework53.common.Resource;
import com.geektech.homework53.data.model.MainResponse;

public interface MainRepository {
    MutableLiveData<Resource<MainResponse>> getWeathers();
}
