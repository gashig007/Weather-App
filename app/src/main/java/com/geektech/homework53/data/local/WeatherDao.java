package com.geektech.homework53.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.geektech.homework53.data.model.WeatherApp;

import java.util.List;

@Dao
public interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeatherApp weather);

    @Query("SELECT * FROM weatherapp")
    List<WeatherApp> getWeather();
}
