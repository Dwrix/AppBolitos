package com.example.AP041_AppBolitos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

public class YoPlanto extends AppCompatActivity {

    EditText mEdtNombre, mEdtEspecie, mEdtUbicacion;
    Button mBtnAgregar, mBtnLista;
    ImageView mImageView;

    final int REQUEST_CODE_GALLERY = 999;

    public static AdminSQLiteOpenHelper mAdminSQLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yo_planto);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Añadir Árbol");

        mEdtNombre = findViewById(R.id.edtNombre);
        mEdtEspecie = findViewById(R.id.edtEspecie);
        mEdtUbicacion = findViewById(R.id.edtUbicacion);
        mBtnAgregar = findViewById(R.id.btnAgregar);
        mBtnLista = findViewById(R.id.btnLista);
        mImageView = findViewById(R.id.imageView);

        //crear la bd
        mAdminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "MIS_ARBOLESDB.sqlite", null, 1);

        //crear tabla de la bd
        mAdminSQLiteOpenHelper.queryData("CREATE TABLE IF NOT EXISTS MIS_ARBOLES(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR, especie VARCHAR, ubicacion VARCHAR, imagen BLOB)");


        //Seleccionar la imagen
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //seleccionar la imagen desde la galeria
                //Permisos para acceder a la memoria del telefono
                ActivityCompat.requestPermissions(
                        YoPlanto.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        //Agregar datos al sqlite
        mBtnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mAdminSQLiteOpenHelper.agregarDatos(
                            mEdtNombre.getText().toString().trim(),
                            mEdtEspecie.getText().toString().trim(),
                            mEdtUbicacion.getText().toString().trim(),
                            ImageViewToByte(mImageView)
                    );
                    Toast.makeText(YoPlanto.this, "Se agrego Correctamente", Toast.LENGTH_SHORT).show();
                    //limpiar campos luego de haber agregado
                    mEdtNombre.setText("");
                    mEdtEspecie.setText("");
                    mEdtUbicacion.setText("");
                    mImageView.setImageResource(R.drawable.addimg);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        //Mostar la lista
        mBtnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(YoPlanto.this, ListaMisArboles.class));

            }
        });

    }

    public static byte[] ImageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //intent galeria
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(this, "No tienes permisos para acceder a la ubicacion del archivo", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON) //habiliar guidlines
            .setAspectRatio(1,1) //Para que la imagen sea cuadrada
            .start(this);
        }
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                //Setear la imagen elegida de la galeria al image view
                mImageView.setImageURI(resultUri);
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }






    public void VolverPaginaPrincipal(View view){
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