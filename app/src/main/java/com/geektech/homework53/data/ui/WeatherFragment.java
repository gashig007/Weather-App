package com.geektech.homework53.data.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.Settings;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.geektech.homework53.R;
import com.geektech.homework53.base.BaseFragment;
import com.geektech.homework53.common.OnItemClick;
import com.geektech.homework53.common.Resource;
import com.geektech.homework53.data.model.MainResponse;
import com.geektech.homework53.data.model.System;
import com.geektech.homework53.data.model.Weather;
import com.geektech.homework53.data.model.WeatherApp;
import com.geektech.homework53.data.model.Wind;
import com.geektech.homework53.databinding.FragmentWeatherBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> implements OnItemClick<Integer> {

    private MainResponse main;
    private System sys;
    private ArrayList<Weather> weatherList = new ArrayList<>();
    private WeatherApp weather;
    private Wind wind;

    private WeatherViewModel weatherViewModel;
    private WeatherFragmentArgs args;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        args = WeatherFragmentArgs.fromBundle(getArguments());

    }

    @Override
    protected FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupViews() {

    }

    @Override
    protected void callRequests() {
        weatherViewModel.getWeatherByCityName(args.getCity());
    }

    @Override
    protected void setupListeners() {
binding.locationTv.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        navController.navigate(R.id.weatherDetailFragment);
    }
});
    }

    @Override
    protected void setupObservers() {
        weatherViewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<WeatherApp>>() {
            @Override
            public void onChanged(Resource<WeatherApp> resource) {
                switch (resource.status) {
                    case LOADING: {
                        binding.cardView.setVisibility(View.GONE);
                        binding.progressBar.setVisibility(View.VISIBLE);
                        binding.imageIv.setVisibility(View.GONE);
                        break;
                    }
                    case SUCCESS: {
                        binding.cardView.setVisibility(View.VISIBLE);
                        binding.progressBar.setVisibility(View.GONE);
                        binding.imageIv.setVisibility(View.VISIBLE);

                        wind = resource.data.getWind();
                        weather = resource.data;
                        main = resource.data.getMain();
                        sys = resource.data.getSys();
                        weatherList = (ArrayList<Weather>) resource.data.getWeather();
                        binding.progressBar.setVisibility(View.GONE);
                        setCurrentWeather();
                        binding.locationTv.setText(resource.data.getName());
                        break;
                    }
                    case ERROR: {
                        Toast.makeText(requireContext(), resource.msg, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setCurrentWeather() {
        SimpleDateFormat realTimeFormat = new SimpleDateFormat("EEEE, dd MMMM y | HH:mm a", Locale.ROOT);
        String realTime = String.valueOf(realTimeFormat.format(java.lang.System.currentTimeMillis()));
        binding.dateTv.setText(realTime);
        binding.weatherStatus.setText(weatherList.get(0).getMain());
        Glide.with(requireContext())
                .load("https://openweathermap.org/img/wn/" + weatherList.get(0).getIcon() + ".png")
                .override(100, 100)
                .into(binding.weatherStatusImg);

        //Setting temperature
        binding.tempTv.setText(Math.round(main.getTemp() - 273.15) + "");
        binding.minTempTv.setText(Math.round(main.getTempMin() - 273.15) + "°C");
        binding.maxTempTv.setText(Math.round(main.getTempMax() - 273.15) + "°C");

        //Setting extras
        binding.humidityTv.setText(main.getHumidity() + "%");
        binding.pressureTv.setText(main.getPressure() + "mBar");
        binding.windTv.setText(wind.getSpeed() + "km/h");

        //Setting sunset and sunrise
        binding.sunriseTv.setText(getTime(requireContext(), Long.valueOf(sys.getSunrise())));
        binding.sunsetTv.setText(getTime(requireContext(), Long.valueOf(sys.getSunset())));

        //Setting daytime
        int daytime = sys.getSunset() - sys.getSunrise();
        binding.daytimeTv.setText(getHours(daytime));
    }

    @SuppressLint("SimpleDateFormat")
    private String getDate(Long date) {
        return new SimpleDateFormat("EEE, d MMMM yyyy | h:mm a").format(date);
    }

    private String getTime(Context context, Long time) {
        return DateUtils.formatDateTime(context, time * 1000, DateUtils.FORMAT_SHOW_TIME);
    }

    private String getHours(int time) {

        int hours = (int) TimeUnit.SECONDS.toHours(time);
        int minutes = (int) ((int) TimeUnit.SECONDS.toMinutes(time) -
                (TimeUnit.SECONDS.toHours(time) * 60));

        return hours + "h " + minutes + "m";
    }

    @Override
    public void onItemClick(Integer data) {

    }
}