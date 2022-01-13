package com.example.AP041_AppBolitos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class FirstFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener  {

    private GoogleMap map;
    private MapView miMapa;
    private Marker markerPrueba;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        miMapa = view.findViewById(R.id.mapView);

        if(miMapa != null){

            miMapa.onCreate(null);
            miMapa.onResume();
            miMapa.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());
        map = googleMap;

        LatLng tienda1 = new LatLng(-33.434467159685205, -70.65444888013099);
        map.addMarker(new MarkerOptions().position(tienda1).title("Tienda").snippet("Las Semillas").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        LatLng tienda2 = new LatLng(-33.43603439478317, -70.66178338128381);
        map.addMarker(new MarkerOptions().position(tienda2).title("Tienda").snippet("Arbolitos").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


        LatLng tienda3 = new LatLng(-33.433187221094, -70.65708951539898);
        map.addMarker(new MarkerOptions().position(tienda3).title("Tienda").snippet("Plantate Uno").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(tienda1, 15));

        LatLng tienda4 = new LatLng(-33.44110051505759, -70.66423370036526);
        markerPrueba = googleMap.addMarker(new MarkerOptions().position(tienda4).title("Tienda").snippet("Don Arbol"));

        googleMap.setOnMarkerClickListener(this);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        String lat, lng;

        if(marker.equals(markerPrueba)){
            lat = Double.toString(markerPrueba.getPosition().latitude);
            lng = Double.toString(markerPrueba.getPosition().longitude);

            Toast.makeText(getActivity(), lat+", "+lng, Toast.LENGTH_SHORT).show();
        }


        return false;
    }

}
