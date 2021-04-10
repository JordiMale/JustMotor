package com.example.justmotor.ui.GetSet;

public class Mix_Oferta {

    private String Foto;
    private String Nombre_Marca;
    private String Nombre_Modelo;
    private String Precio;

    //Constructor
    public Mix_Oferta(String foto, String nombre_Marca, String nombre_Modelo, String precio) {
        this.Foto = foto;
        this.Nombre_Marca = nombre_Marca;
        this.Nombre_Modelo = nombre_Modelo;
        this.Precio = precio;
    }

    //Getters i Setters
    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getNombre_Marca() {
        return Nombre_Marca;
    }

    public void setNombre_Marca(String nombre_Marca) {
        Nombre_Marca = nombre_Marca;
    }

    public String getNombre_Modelo() {
        return Nombre_Modelo;
    }

    public void setNombre_Modelo(String nombre_Modelo) {
        Nombre_Modelo = nombre_Modelo;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }
}
