package com.example.edgar.mysql_androidstudio;

public class comidas {

    private String nombre;
    private String descripcion;
    private int precio;
    private String imagen;

    public comidas(String nombre, String descripcion, int precio, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
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




}
