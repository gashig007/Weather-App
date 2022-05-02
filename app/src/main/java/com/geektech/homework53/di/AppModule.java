package com.geektech.homework53.di;

import android.content.Context;

import androidx.room.Room;

import com.geektech.homework53.data.local.AppDataBase;
import com.geektech.homework53.data.local.WeatherDao;
import com.geektech.homework53.data.remote.WeatherApi;
import com.geektech.homework53.data.repositories.MainRepositoryImpl;
import com.geektech.homework53.domain.repository.MainRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {
    @Provides
    public static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Provides
    public static Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    public static WeatherApi provideApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }

    @Provides
    @Singleton
    public static MainRepository proviteMainRepository(WeatherApi api, WeatherDao dao) {
        return new MainRepositoryImpl(api, dao);
    }

    @Provides
    public static AppDataBase provideDataBase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, "database").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }

    @Provides
    public static WeatherDao provideDao(AppDataBase dataBase) {
        return dataBase.weatherDao();
    }
}
