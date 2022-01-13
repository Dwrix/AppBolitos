package com.example.AP041_AppBolitos;

public class Modelo {
    private int id;
    private String nombre;
    private String especie;
    private String ubicacion;
    private byte[] imagen;

    public Modelo(int id, String nombre, String especie, String ubicacion, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.ubicacion = ubicacion;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
