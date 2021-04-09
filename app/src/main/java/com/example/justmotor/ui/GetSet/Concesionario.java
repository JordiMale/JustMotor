package com.example.justmotor.ui.GetSet;

public class Concesionario {

    private int Id_Concesionario;
    private String Modelo;
    private String Nombre_Empresa;
    private int Oferta;
    private String CIF;
    private String Dirección;

    //Constructor.
    public Concesionario(int id_Concesionario, String modelo, String nombre_Empresa, int oferta, String CIF, String dirección) {
        Id_Concesionario = id_Concesionario;
        Modelo = modelo;
        Nombre_Empresa = nombre_Empresa;
        Oferta = oferta;
        this.CIF = CIF;
        Dirección = dirección;
    }

    //Getters i setters
    public int getId_Concesionario() {
        return Id_Concesionario;
    }

    public void setId_Concesionario(int id_Concesionario) {
        Id_Concesionario = id_Concesionario;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getNombre_Empresa() {
        return Nombre_Empresa;
    }

    public void setNombre_Empresa(String nombre_Empresa) {
        Nombre_Empresa = nombre_Empresa;
    }

    public int getOferta() {
        return Oferta;
    }

    public void setOferta(int oferta) {
        Oferta = oferta;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getDirección() {
        return Dirección;
    }

    public void setDirección(String dirección) {
        Dirección = dirección;
    }
}

