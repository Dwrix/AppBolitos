package com.example.AP041_AppBolitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Certificado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificado);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menuOpciones){
        getMenuInflater().inflate(R.menu.menu, menuOpciones);
        return true;
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