package com.example.edgar.mysql_androidstudio;

public class comidas {





    private String idclienteapp;

    private String nombre;
    private String descripcion;
    private int precio;
    private String imagen;
    private String indicadorID;

    public comidas(String nombre, String descripcion, int precio, String imagen, String indicadorID) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.indicadorID = indicadorID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public String getImagen() {
        return imagen;
    }

    public String getIndicadorID() {
        return indicadorID;
    }










}
