package com.example.AP041_AppBolitos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //AGREGAR

    public void agregarDatos(String nombre, String especie, String ubicacion, byte[] imagen){
        SQLiteDatabase database = getWritableDatabase();
        //Query para registrar los datos a la bd
        String sql = "INSERT INTO MIS_ARBOLES VALUES(NULL, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,nombre);
        statement.bindString(2,especie);
        statement.bindString(3,ubicacion);
        statement.bindBlob(4,imagen);

        statement.executeInsert();
    }

    //MODIDICAR
    public void modificarDatos(String nombre, String especie, String ubicacion, byte[] imagen, int id){
        SQLiteDatabase database = getWritableDatabase();
        //query para modificar
        String sql = "UPDATE MIS_ARBOLES SET nombre=?, especie=?, ubicacion=?, imagen=? WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1,nombre);
        statement.bindString(2,especie);
        statement.bindString(3,ubicacion);
        statement.bindBlob(4,imagen);
        statement.bindDouble(5, (double)id);

        statement.execute();
        database.close();
    }

    //ELIMINAR
    public void eliminarDatos(int id){
            SQLiteDatabase database = getWritableDatabase();
            //query para eliminar usando el id
            String sql = "DELETE FROM MIS_ARBOLES WHERE id=?";

            SQLiteStatement statement = database.compileStatement(sql);
            statement.clearBindings();
            statement.bindDouble(1, (double)id);

            statement.execute();
            database.close();
    }

    public Cursor getDatos(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Usuario (" +
                "Id_Usuario integer PRIMARY KEY AUTOINCREMENT," +
                "Nombre varchar," +
                "Password varchar," +
                "Email varchar," +
                "Imagen varchar)";

        db.execSQL(sql);

        String sql2 = "CREATE TABLE Tienda (" +
                "Id_Tienda integer PRIMARY KEY AUTOINCREMENT," +
                "Nombre_Tienda varchar," +
                "Direccion varchar," +
                "Url_Ubicacion varchar," +
                "Favorito boolean)";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
