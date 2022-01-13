package com.example.AP041_AppBolitos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etCorreLogin, etPasswordLogin;
    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCorreLogin = (EditText)findViewById(R.id.inputEmail);
        etPasswordLogin = (EditText)findViewById(R.id.etContrasena);

    }

    public void buscarUserValido(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "registro",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String correo = etCorreLogin.getText().toString();
        String password = etPasswordLogin.getText().toString();

        Cursor datoObtenido =  db.rawQuery("select * from Usuario where Email = '"+correo+"' and Password ='"+password+"'",null);

        if(datoObtenido.moveToFirst()){
            idUser = datoObtenido.getInt(0);
            limpiarDatos();
            openPaginaPrincipal(view);
            Toast.makeText(this,"Bienvenido",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Usuario No Registrado",Toast.LENGTH_SHORT).show();
        }
        db.close();

    }

    public void limpiarDatos(){
        etCorreLogin.setText("");
        etPasswordLogin.setText("");
    }

    public void openPaginaPrincipal(View view) {
        Intent PaginaPrincipal = new Intent(this, PaginaPrincipla.class);
        //junto al Intent enviamos el id del user que ha ingresado a la aplicacion
        PaginaPrincipal.putExtra("id",idUser);//metodo para agregarle un dato al objeto Intent
        startActivity(PaginaPrincipal);
    }

    public void openRegistro(View view) {
        Intent Registro = new Intent(this, Registro.class);
        limpiarDatos();
        startActivity(Registro);
    }

}