package com.geektech.homework53.data.local.converters;

import androidx.room.TypeConverter;

import com.geektech.homework53.data.model.Wind;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class WindConverter {
    @TypeConverter
    public String fromWindToString(Wind main){
        Gson gson = new Gson();
        Type type = new TypeToken<Wind>(){}.getType();
        return gson.toJson(main,type);
    }
    @TypeConverter
    public Wind fromStringToWind(String fromString){
        Gson gson = new Gson();
        Type type = new TypeToken<Wind>(){}.getType();
        return gson.fromJson(fromString,type);
    }
}
