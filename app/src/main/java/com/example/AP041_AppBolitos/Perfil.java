package com.example.AP041_AppBolitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Perfil extends AppCompatActivity {

    private int idUser;
    private TextView nombre, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nombre = (TextView)findViewById(R.id.TvNombres);
        correo = (TextView)findViewById(R.id.TvCorreo);

        //validamos y recuperamos el dato  que viene junto al objeto intent
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

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "registro",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor datoObtenido =  db.rawQuery("select * from Usuario where Id_Usuario = "+idUser,null);

        if(datoObtenido.moveToFirst()){
            nombre.setText(datoObtenido.getString(1));
            correo.setText(datoObtenido.getString(3));
        }else{
            Toast.makeText(this,"Lo sentimos no pudimos cargar tus datos",Toast.LENGTH_SHORT).show();
        }

        db.close();

    }

    public void openCertificado(View view){
        Intent certificado = new Intent(this, Certificado.class);
        startActivity(certificado);
    }

    public void close(View view) {
        finish();
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

