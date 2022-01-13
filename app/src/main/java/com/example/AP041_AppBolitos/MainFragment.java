package com.example.AP041_AppBolitos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainFragment extends  Fragment {

    private ListView listaTiendas;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container,false);

        String[] nombre = {"Tienda Las Semillas","Tienda Arbolitos","Tienda Plantate Uno","Tienda Don Arbol"};

        listaTiendas = (ListView) view.findViewById(R.id.lvListaTiendas);

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nombre);
        listaTiendas.setAdapter(adapter);
        listaTiendas.setDividerHeight(10);

        return view;
    }

}
