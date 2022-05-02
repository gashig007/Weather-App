package com.geektech.homework53.data.local.converters;

import androidx.room.TypeConverter;

import com.geektech.homework53.data.model.System;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SystemConverter {
    @TypeConverter
    public String fromSysToString(System main){
        Gson gson = new Gson();
        Type type = new TypeToken<System>(){}.getType();
        return gson.toJson(main,type);
    }
    @TypeConverter
    public System fromStringToSys(String fromString){
        Gson gson = new Gson();
        Type type = new TypeToken<System>(){}.getType();
        return gson.fromJson(fromString,type);
    }
}
