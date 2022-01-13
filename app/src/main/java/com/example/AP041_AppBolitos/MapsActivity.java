package com.example.AP041_AppBolitos;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng ReservaCarlota = new LatLng(-44.531790, -71.489110);
        mMap.addMarker(new MarkerOptions().position(ReservaCarlota).title("Reserva Nacional").snippet("Lago Carlota").icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)));

        LatLng ReservaCoyhaique1 = new LatLng(-45.534610, -72.021910);
        mMap.addMarker(new MarkerOptions().position(ReservaCoyhaique1).title("Reserva Coyhaique ").snippet("Lago Llanquihue").icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)));

        LatLng ReservaCoyhaique2 = new LatLng(-45.598518, -72.209058);
        mMap.addMarker(new MarkerOptions().position(ReservaCoyhaique2).title("Reserva Coyhaique ").snippet("Río Simpson").icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ReservaCoyhaique1, 7));


    }
}