package com.geektech.homework53.data.local.converters;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.geektech.homework53.data.model.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListConverter {
    @TypeConverter
    public static String jsonToList(List<Weather> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public static List<Weather> listToJson(String value) {
        Type listType = new TypeToken<List<Weather>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }
}
