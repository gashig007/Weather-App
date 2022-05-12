package com.geektech.homework53.data.ui.weather_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.geektech.homework53.R;
import com.geektech.homework53.base.BaseFragment;
import com.geektech.homework53.databinding.FragmentWeatherDetailBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class WeatherDetailFragment extends BaseFragment<FragmentWeatherDetailBinding> implements OnMapReadyCallback {
    private ArrayList<LatLng> markers = new ArrayList<>();
    private Marker marker;

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
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.google_map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.google_map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void callRequests() {
    }

    @Override
    protected void setupListeners() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edittextWeather.getText().toString().isEmpty()) {
                    Toast.makeText(requireActivity(), "Напишите город", Toast.LENGTH_SHORT).show();
                } else {
               //     navController.navigate(WeatherDetailFragmentDirections.actionWeatherDetailFragmentToWeatherFragment());
                }
            }
        });
    }

    @Override
    protected void setupObservers() {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        /*googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));*/
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                googleMap.clear();
                MarkerOptions options = new MarkerOptions();
                options.draggable(true);
                options.title(latLng.toString());
                options.position(latLng);
                googleMap.addMarker(options);
                googleMap.animateCamera(getCameraUpdate(latLng, 4f, 90f, 30f));
                googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(@NonNull Marker marker) {
                        navController.navigate(WeatherDetailFragmentDirections.actionWeatherDetailFragmentToWeatherFragment(String.valueOf(marker.getPosition().latitude), String.valueOf(marker.getPosition().longitude)));
                        Log.e("TAG",  String.valueOf(marker.getPosition().latitude));
                    }
                });
            }
        });

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                marker.showInfoWindow();
                return false;
            }
        });

        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {
                marker.setTitle(marker.getPosition().toString());
                marker.showInfoWindow();
            }

            @Override
            public void onMarkerDragStart(@NonNull Marker marker) {
                marker.hideInfoWindow();
            }
        });
    }

    private CameraUpdate getCameraUpdate(LatLng latLng, float zoom, float bearing, float titl) {
        return CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(latLng)
                .zoom(zoom).bearing(bearing).tilt(titl).build());
    }
}
