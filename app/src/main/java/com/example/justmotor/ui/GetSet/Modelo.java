package com.example.justmotor.ui.GetSet;

public class Modelo {

    private int Id_Modelo;
    private int Marca;
    private int Ficha_Tecnica;
    private String Nombre_Modelo;
    private String Tipo_Modelo;
    private String Descripcion;

    //Constructor
    public Modelo(int id_Modelo, int marca, int ficha_Tecnica, String nombre_Modelo, String tipo_Modelo, String descripcion) {
        Id_Modelo = id_Modelo;
        Marca = marca;
        Ficha_Tecnica = ficha_Tecnica;
        Nombre_Modelo = nombre_Modelo;
        Tipo_Modelo = tipo_Modelo;
        Descripcion = descripcion;
    }

    //Getter i Setter
    public int getId_Modelo() {
        return Id_Modelo;
    }

    public void setId_Modelo(int id_Modelo) {
        Id_Modelo = id_Modelo;
    }

    public int getMarca() {
        return Marca;
    }

    public void setMarca(int marca) {
        Marca = marca;
    }

    public int getFicha_Tecnica() {
        return Ficha_Tecnica;
    }

    public void setFicha_Tecnica(int ficha_Tecnica) {
        Ficha_Tecnica = ficha_Tecnica;
    }

    public String getNombre_Modelo() {
        return Nombre_Modelo;
    }

    public void setNombre_Modelo(String nombre_Modelo) {
        Nombre_Modelo = nombre_Modelo;
    }

    public String getTipo_Modelo() {
        return Tipo_Modelo;
    }

    public void setTipo_Modelo(String tipo_Modelo) {
        Tipo_Modelo = tipo_Modelo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
