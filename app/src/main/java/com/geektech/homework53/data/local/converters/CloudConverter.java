package com.geektech.homework53.data.local.converters;

import androidx.room.TypeConverter;

import com.geektech.homework53.data.model.Image;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class CloudConverter {
    @TypeConverter
    public String fromCloudsToString(Image image){
        Gson gson = new Gson();
        Type type = new TypeToken<Image>(){}.getType();
        return gson.toJson(image,type);
    }
    @TypeConverter
    public Image fromStringToClouds(String fromString){
        Gson gson = new Gson();
        Type type = new TypeToken<Image>(){}.getType();
        return gson.fromJson(fromString,type);
    }
}
