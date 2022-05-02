package com.geektech.homework53.data.local.converters;

import androidx.room.TypeConverter;

import com.geektech.homework53.data.model.MainResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MainConverter {
    @TypeConverter
    public String fromMainToString(MainResponse main){
        Gson gson = new Gson();
        Type type = new TypeToken<MainResponse>(){}.getType();
        return gson.toJson(main,type);
    }
    @TypeConverter
    public MainResponse fromStringToMain(String fromString){
        Gson gson = new Gson();
        Type type = new TypeToken<MainResponse>(){}.getType();
        return gson.fromJson(fromString,type);
    }
}
