package com.example.AP041_AppBolitos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static com.example.AP041_AppBolitos.YoPlanto.mAdminSQLiteOpenHelper;

public class ListaMisArboles extends AppCompatActivity {

    ListView mListView;
    ArrayList<Modelo> mLista;
    MisArbolesListAdapter mAdapter = null;

    ImageView imageViewIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mis_arboles);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Mis Arboles");


        mListView = findViewById(R.id.listView);
        mLista = new ArrayList<>();
        mAdapter = new MisArbolesListAdapter(this, R.layout.fila, mLista);
        mListView.setAdapter(mAdapter);

        //tomar toda los datos de sqlite
        Cursor cursor = mAdminSQLiteOpenHelper.getDatos("SELECT * FROM MIS_ARBOLES");
        mLista.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String especie = cursor.getString(2);
            String ubicacion = cursor.getString(3);
            byte[] imagen = cursor.getBlob(4);
            //agregar a la lista

            mLista.add(new Modelo(id, nombre, especie, ubicacion, imagen));
        }
        mAdapter.notifyDataSetChanged();
        if (mLista.size()==0){
            //si no hay ningun dato en la tabla de la bd, listview vacia
            Toast.makeText(this,"No hay datos encontrados", Toast.LENGTH_SHORT).show();
        }

        //Itemlongclick = al mentener presionado el item
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {
                //Al hacer seleccionar un item de la lista aparecera 2 opciones: modificar y eliminar
                final CharSequence[] item = {"Modificar", "Eliminar"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(ListaMisArboles.this);

                dialog.setTitle("Seleccione una opcion");
                dialog.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        if (which == 0){
                            //Modificar
                            Cursor c = mAdminSQLiteOpenHelper.getDatos("SELECT id FROM MIS_ARBOLES");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            //Mostrar Dialog para modificar
                            MostrarDialogModificar(ListaMisArboles.this, arrID.get(position));
                        }
                        if (which == 1){
                            //Eliminar
                            Cursor c = mAdminSQLiteOpenHelper.getDatos("SELECT id FROM MIS_ARBOLES");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            MostrarDialogEliminar(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });




    }

    private void MostrarDialogEliminar(final int idMis_Arboles) {
        AlertDialog.Builder dialogEliminar = new AlertDialog.Builder(ListaMisArboles.this);
        dialogEliminar.setTitle("¡Cuidado!");
        dialogEliminar.setMessage("¿Estas seguro que lo quieres eliminar?");
        dialogEliminar.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    mAdminSQLiteOpenHelper.eliminarDatos(idMis_Arboles);
                    Toast.makeText(ListaMisArboles.this, "Se ha eliminado Correctamente", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                listaModificarMisArboles();
            }
        });
        dialogEliminar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogEliminar.show();
    }

    private void MostrarDialogModificar(Activity activity, final int position){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.modificar_dialog);
        dialog.setTitle("Modificar");

        imageViewIcon = dialog.findViewById(R.id.imageViewMisArboles);
        final EditText edtNombre = dialog.findViewById(R.id.edtNombre);
        final EditText edtEspecie = dialog.findViewById(R.id.edtEspecie);
        final EditText edtUbicacion = dialog.findViewById(R.id.edtUbicacion);
        Button btnModificar = dialog.findViewById(R.id.btnModificar);

        //Ancho y alto del dialogo
        int ancho = (int)(activity.getResources().getDisplayMetrics().widthPixels*0.95);
        int alto = (int)(activity.getResources().getDisplayMetrics().heightPixels*0.7);
        dialog.getWindow().setLayout(ancho,alto);
        dialog.show();

        //para cambiar la imagen al hacer click de la ventana de dialogo modificar
        imageViewIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Permisos para acceder a la memoria del telefono
                ActivityCompat.requestPermissions(
                        ListaMisArboles.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                            mAdminSQLiteOpenHelper.modificarDatos(
                            edtNombre.getText().toString().trim(),
                            edtEspecie.getText().toString().trim(),
                            edtUbicacion.getText().toString().trim(),
                            YoPlanto.ImageViewToByte(imageViewIcon),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Modificado correctamente", Toast.LENGTH_SHORT).show();
                }
                catch (Exception error){
                    Log.e("Error al modificar", error.getMessage());
                }
                listaModificarMisArboles();
            }
        });

    }

    private void listaModificarMisArboles() {
        //Tomar todos los datos de sqlite
        Cursor cursor = mAdminSQLiteOpenHelper.getDatos("SELECT * FROM MIS_ARBOLES");
        mLista.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String especie = cursor.getString(2);
            String ubicacion = cursor.getString(3);
            byte[] imagen = cursor.getBlob(4);

            mLista.add(new Modelo(id,nombre,especie,ubicacion,imagen));
        }
        mAdapter.notifyDataSetChanged();

    }


    public static byte[]    ImageViewToByte(ImageView imagen) {
        Bitmap bitmap = ((BitmapDrawable)imagen.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 888) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //intent galeria
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 888);
            } else {
                Toast.makeText(this, "No tienes permisos para acceder a la ubicacion del archivo", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 888 && resultCode == RESULT_OK){
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
                imageViewIcon.setImageURI(resultUri);
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}