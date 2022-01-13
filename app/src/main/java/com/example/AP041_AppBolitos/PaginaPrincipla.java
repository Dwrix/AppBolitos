package com.example.AP041_AppBolitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class PaginaPrincipla extends AppCompatActivity {

    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principla);

        //validamos y recuperamos el dato (id) que viene en el objeto Intent
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                idUser= 0;
            } else {
                idUser= extras.getInt("id");
            }
        } else {
            idUser= (int) savedInstanceState.getSerializable("id");
        }



        ImageSlider slider = findViewById(R.id.slider1);
        List<SlideModel> listaSlider = new ArrayList<>();

        listaSlider.add(new SlideModel(R.drawable.imagennoticia1,"Como empezar"));
        listaSlider.add(new SlideModel(R.drawable.imagennoticia2,"Nuevos Convenios"));
        listaSlider.add(new SlideModel(R.drawable.imagennoticia1,"Que Gano Plantando?"));
        listaSlider.add(new SlideModel(R.drawable.imagennoticia2,"Arboles Nativos"));
        slider.setImageList(listaSlider,true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menuOpciones){
        getMenuInflater().inflate(R.menu.menu, menuOpciones);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        int idUsuario = idUser;
        int id= item.getItemId();
        switch (id){
            case R.id.Home:
                Intent Home = new Intent(this, PaginaPrincipla.class);
                startActivity(Home);
                break;
            case R.id.Informate:
                Intent info = new Intent(this, Informate.class);
                startActivity(info);

                break;
            case R.id.Tiendas:
                Intent Tiendas = new Intent(this, Tiendas.class);
                startActivity(Tiendas);
                break;

            case R.id.Perfil:
                Intent Perfil = new Intent(this, Perfil.class);
                Perfil.putExtra("id",idUsuario);
                startActivity(Perfil);
                break;

            case R.id.Denunciar:
                Intent Denunciar = new Intent(this, Denunciar.class);
                startActivity(Denunciar);
                break;
        }

        return super.onOptionsItemSelected(item);

    }

    public void openMapa(View view) {
        Intent irMapa = new Intent(this, MapsActivity.class);
        startActivity(irMapa);
    }

    public void openYoPlanto(View view) {
        Intent yoPlanto = new Intent(this, YoPlanto.class);
        startActivity(yoPlanto);
    }

}