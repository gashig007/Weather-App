package com.geektech.homework53.data.ui.weather_detail;

import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.geektech.homework53.base.BaseFragment;
import com.geektech.homework53.databinding.FragmentWeatherDetailBinding;

public class WeatherDetailFragment extends BaseFragment<FragmentWeatherDetailBinding>{

    @Override
    protected FragmentWeatherDetailBinding bind() {
        return FragmentWeatherDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupViews() {
    }

    @Override
    protected void callRequests() {

    }

    @Override
    protected void setupListeners() {
binding.button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (binding.edittext.getText().toString().isEmpty()){
            Toast.makeText(requireActivity(), "Напишите город", Toast.LENGTH_SHORT).show();
        } else {
            navController.navigate(WeatherDetailFragmentDirections.actionWeatherDetailFragmentToWeatherFragment(binding.edittext.getText().toString()));
        }
    }
});
    }

    @Override
    protected void setupObservers() {

    }
}
