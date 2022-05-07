package com.geektech.homework53.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import androidx.room.TypeConverters;

import com.geektech.homework53.data.local.converters.CloudConverter;
import com.geektech.homework53.data.local.converters.CoordConverter;
import com.geektech.homework53.data.local.converters.InfoTypeConverter;
import com.geektech.homework53.data.local.converters.ListConverter;
import com.geektech.homework53.data.local.converters.MainConverter;
import com.geektech.homework53.data.local.converters.SystemConverter;
import com.geektech.homework53.data.local.converters.WindConverter;
import com.geektech.homework53.data.model.WeatherApp;

@Database(entities = {WeatherApp.class}, version = 1)
@TypeConverters({InfoTypeConverter.class, CloudConverter.class, CoordConverter.class,
        MainConverter.class, SystemConverter.class, WindConverter.class, ListConverter.class})
public abstract class AppDataBase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}
