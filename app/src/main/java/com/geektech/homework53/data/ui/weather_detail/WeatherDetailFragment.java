package com.geektech.homework53.data.ui.weather_detail;

import android.graphics.Bitmap;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.geektech.homework53.base.BaseFragment;
import com.geektech.homework53.common.Resource;
import com.geektech.homework53.data.model.Weather;
import com.geektech.homework53.databinding.FragmentWeatherDetailBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class WeatherDetailFragment extends BaseFragment<FragmentWeatherDetailBinding> {
    private WeatherDetailViewModel viewModel;

    @Override
    protected FragmentWeatherDetailBinding bind() {
        return FragmentWeatherDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherDetailViewModel.class);
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
                if (binding.edittext.getText().toString().isEmpty()) {
                    Toast.makeText(requireActivity(), "Напишите город", Toast.LENGTH_SHORT).show();
                } else {
                    navController.navigate(WeatherDetailFragmentDirections.actionWeatherDetailFragmentToWeatherFragment(binding.edittext.getText().toString()));
                }
            }
        });
    }

    @Override
    protected void setupObservers() {
        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<Weather>>() {
            @Override
            public void onChanged(Resource<Weather> weatherResource) {
                switch (weatherResource.status) {
                    case SUCCESS:
                        Weather weather = weatherResource.data;
                        binding.tvWeatherText.setText(weather.getMain());
                        binding.tvWeatherStatus.setText(weather.getDescription());
                        Glide.with(binding.getRoot()).load(weather.getIcon())
                                .centerCrop().into(binding.ivWeather);
                        break;
                    case ERROR:
                        baseState();
                        Snackbar.make(binding.getRoot(), weatherResource.msg, BaseTransientBottomBar.LENGTH_LONG);
                        break;
                    case LOADING:
                        loadingState();
                        break;
                }
            }
        });
    }

    private void generateQr(int weatherId){
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(String.valueOf(weatherId), BarcodeFormat.QR_CODE, 200, 200);
            binding.ivGr.setImageBitmap(bitmap);
        } catch (Exception e){
            Log.e("Tag", "generateQR" + e.getLocalizedMessage());
        }

    }

    private void loadingState() {
        binding.tvWeatherStatus.setVisibility(View.GONE);
        binding.tvWeatherText.setVisibility(View.GONE);
        binding.ivWeather.setVisibility(View.GONE);
        binding.ivGr.setVisibility(View.GONE);
        binding.progress.setVisibility(View.VISIBLE);
    }

    private void baseState() {
        binding.tvWeatherStatus.setVisibility(View.VISIBLE);
        binding.tvWeatherText.setVisibility(View.VISIBLE);
        binding.ivWeather.setVisibility(View.VISIBLE);
        binding.ivGr.setVisibility(View.VISIBLE);
        binding.progress.setVisibility(View.GONE);
    }
}
