package com.example.AP041_AppBolitos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MisArbolesListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Modelo> ListaMisArboles;

    public MisArbolesListAdapter(Context context, int layout, ArrayList<Modelo> listaMisArboles) {
        this.context = context;
        this.layout = layout;
        ListaMisArboles = listaMisArboles;
    }

    @Override
    public int getCount() {
        return ListaMisArboles.size();
    }

    @Override
    public Object getItem(int i) {
        return ListaMisArboles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtNombre, txtEspecie, txtUbicacion;
    }

    @Override
    public View getView(int i, View view, ViewGroup ViewGroup) {
        View fila = view;
        //El viewholder se utiliza para evitar volver a llamar al metodo findviewid cada vez
        //Que se tenga que mostrar algo.
        ViewHolder holder = new ViewHolder();

        if (fila==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            fila = inflater.inflate(layout, null);
            holder.txtNombre = fila.findViewById(R.id.txtNombre);
            holder.txtEspecie = fila.findViewById(R.id.txtEspecie);
            holder.txtUbicacion = fila.findViewById(R.id.txtUbicacion);
            holder.imageView = fila.findViewById(R.id.imgIcon);
            fila.setTag(holder);
        }
        else{
            holder = (ViewHolder)fila.getTag();
        }

        Modelo modelo = ListaMisArboles.get(i);

        holder.txtNombre.setText(modelo.getNombre());
        holder.txtEspecie.setText(modelo.getEspecie());
        holder.txtUbicacion.setText(modelo.getUbicacion());

        byte[] misArbolesImagen = modelo.getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(misArbolesImagen, 0, misArbolesImagen.length);
        holder.imageView.setImageBitmap(bitmap);

        return fila;

    }
}
