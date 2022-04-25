package com.geektech.homework53;

import android.app.Application;

import com.geektech.homework53.data.remote.RetrofitClient;

public class App extends Application {
    private RetrofitClient retrofitClient;
    public static MainRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        repository = new MainRepository(retrofitClient.provideApi());
    }
}
