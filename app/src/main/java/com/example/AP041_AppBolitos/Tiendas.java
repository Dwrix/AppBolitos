package com.example.AP041_AppBolitos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.text.DateFormatSymbols;

public class Tiendas extends AppCompatActivity {

    FragmentTransaction transaction;
    Fragment fragmentListaTiendas, fragmentMapaTiendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiendas);

        fragmentListaTiendas = new MainFragment();
        fragmentMapaTiendas = new FirstFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragment, fragmentMapaTiendas ).commit();
    }
    public void Volver(View view){

        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menuOpciones){
        getMenuInflater().inflate(R.menu.menu, menuOpciones);
        return true;
    }

    public void abrir(View view){

        transaction = getSupportFragmentManager().beginTransaction();

        switch (view.getId()){

            case R.id.btnMapaTiendas:

                transaction.replace(R.id.contenedorFragment, fragmentMapaTiendas);
                transaction.addToBackStack(null);

                break;
            case R.id.btnListaTiendas:

                transaction.replace(R.id.contenedorFragment, fragmentListaTiendas);
                transaction.addToBackStack(null);
                break;

        }

        transaction.commit();
    }

    public boolean onOptionsItemSelected(MenuItem item){

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
                startActivity(Perfil);
                break;

            case R.id.Denunciar:
                Intent Denunciar = new Intent(this, Denunciar.class);
                startActivity(Denunciar);
                break;
        }

        return super.onOptionsItemSelected(item);

    }

}