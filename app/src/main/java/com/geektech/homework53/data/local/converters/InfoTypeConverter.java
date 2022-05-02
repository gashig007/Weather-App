package com.geektech.homework53.data.local.converters;

import androidx.room.TypeConverter;

import com.geektech.homework53.data.model.Weather;
import com.geektech.homework53.data.model.WeatherApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class InfoTypeConverter {
    @TypeConverter
    public String fromInfoToString(Weather info) {
        Gson gson = new Gson();
        Type type = new TypeToken<Weather>() {
        }.getType();
        return gson.toJson(info, type);
    }

    public String fromStringToInfo(String info) {
        Gson gson = new Gson();
        Type type = new TypeToken<Weather>() {
        }.getType();
        return gson.toJson(info, type);
    }
}
