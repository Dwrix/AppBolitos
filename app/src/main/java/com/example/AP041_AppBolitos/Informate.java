package com.example.AP041_AppBolitos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Informate extends AppCompatActivity {

    FragmentTransaction transaction;
    Fragment mainFragmentInformate, CuidarArbolFragment, ComoAportarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informate);

        //instanciar los fragment
        mainFragmentInformate = new MainFragmentInformate();
        CuidarArbolFragment = new CuidarArbolFragment();
        ComoAportarFragment = new ComoAportarFragment();

        // para poder abrir los fragment
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragment, mainFragmentInformate).commit();
    }

    public void abrir(View view){

        transaction = getSupportFragmentManager().beginTransaction();

        switch (view.getId()){
            case R.id.btnCuidarArbol:
                transaction.replace(R.id.contenedorFragment, CuidarArbolFragment);
                transaction.addToBackStack(null);
                break;
            case R.id.btnComoAportar:
                transaction.replace(R.id.contenedorFragment, ComoAportarFragment);
                transaction.addToBackStack(null);
                break;
        }
        transaction.commit();

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