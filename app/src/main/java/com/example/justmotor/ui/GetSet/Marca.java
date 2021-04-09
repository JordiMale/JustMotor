package com.example.justmotor.ui.GetSet;

public class Marca {
    private int Id_Marca;
    private String Nombre_Marca;

    //Constructor
    public Marca(int id_Marca, String nombre_Marca) {
        Id_Marca = id_Marca;
        Nombre_Marca = nombre_Marca;
    }

    //Getters i setters
    public int getId_Marca() {
        return Id_Marca;
    }

    public void setId_Marca(int id_Marca) {
        Id_Marca = id_Marca;
    }

    public String getNombre_Marca() {
        return Nombre_Marca;
    }

    public void setNombre_Marca(String nombre_Marca) {
        Nombre_Marca = nombre_Marca;
    }
}
