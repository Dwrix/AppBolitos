package com.example.AP041_AppBolitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author: Rodrigo Quintuman
 */

public class Registro extends AppCompatActivity {

    private EditText etNombreUser, etCorreo, etPassword, etConfirmarPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombreUser = (EditText)findViewById(R.id.etNombreUser);
        etCorreo = (EditText)findViewById(R.id.etCorreo);
        etPassword = (EditText)findViewById(R.id.etPassworUser);
        etConfirmarPass = (EditText)findViewById(R.id.etConfirmarPassUser);

    }

    public void registrarUser(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "registro",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        //validamso que los campos no se encuentren vacios
        if(etNombreUser.getText().toString().equals("") || etCorreo.getText().toString().equals("") || etPassword.getText().toString().equals("") || etConfirmarPass.getText().toString().equals("")){

            Toast.makeText(this,"Hay Campos vacios",Toast.LENGTH_SHORT).show();

        }else{
            //Validamos que ambas contraseñas sean validas
            if(etPassword.getText().toString().equals(etConfirmarPass.getText().toString())){
                ContentValues reg = new ContentValues();
                reg.put("Nombre",etNombreUser.getText().toString());
                reg.put("Password", etPassword.getText().toString());
                reg.put("Email",etCorreo.getText().toString());

                //insertamos los datos en la tabla usuario
                db.insert("Usuario", null,reg);
                db.close();

                Toast.makeText(this,"Tu Cuenta se creo exitosamente",Toast.LENGTH_SHORT).show();

                //limpiamos los campos y lo llevamos a la pagina de registro para que inicie sesion
                limpiarCampos();
                cancelarRegistro(view);
            }else{

                Toast.makeText(this,"Verifica que la contraseña coincida",Toast.LENGTH_SHORT).show();

            }
        }


    }

    public void limpiarCampos(){
        etNombreUser.setText("");
        etPassword.setText("");
        etConfirmarPass.setText("");
        etCorreo.setText("");
    }

    public void openRegistroToPaginaPrincipal(View view) {
        Intent PaginaPrincipal = new Intent(this, PaginaPrincipla.class);
        startActivity(PaginaPrincipal);
    }



    public void cancelarRegistro(View view){
        finish();
    }
}