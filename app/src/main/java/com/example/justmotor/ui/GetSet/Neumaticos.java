package com.example.justmotor.ui.GetSet;

public class Neumaticos {

    private int Id_Dimensiones;
    private double Longitud_Total;
    private double Ancho_Total;
    private double Altura_Total;
    private double Distancia_Entre_Ejes;
    private double Altura_Dese_el_suelo;

    //Constructor
    public Neumaticos(int id_Dimensiones, double longitud_Total, double ancho_Total, double altura_Total, double distancia_Entre_Ejes, double altura_Dese_el_suelo) {
        Id_Dimensiones = id_Dimensiones;
        Longitud_Total = longitud_Total;
        Ancho_Total = ancho_Total;
        Altura_Total = altura_Total;
        Distancia_Entre_Ejes = distancia_Entre_Ejes;
        Altura_Dese_el_suelo = altura_Dese_el_suelo;
    }

    //Getters i setters
    public int getId_Dimensiones() {
        return Id_Dimensiones;
    }

    public void setId_Dimensiones(int id_Dimensiones) {
        Id_Dimensiones = id_Dimensiones;
    }

    public double getLongitud_Total() {
        return Longitud_Total;
    }

    public void setLongitud_Total(double longitud_Total) {
        Longitud_Total = longitud_Total;
    }

    public double getAncho_Total() {
        return Ancho_Total;
    }

    public void setAncho_Total(double ancho_Total) {
        Ancho_Total = ancho_Total;
    }

    public double getAltura_Total() {
        return Altura_Total;
    }

    public void setAltura_Total(double altura_Total) {
        Altura_Total = altura_Total;
    }

    public double getDistancia_Entre_Ejes() {
        return Distancia_Entre_Ejes;
    }

    public void setDistancia_Entre_Ejes(double distancia_Entre_Ejes) {
        Distancia_Entre_Ejes = distancia_Entre_Ejes;
    }

    public double getAltura_Dese_el_suelo() {
        return Altura_Dese_el_suelo;
    }

    public void setAltura_Dese_el_suelo(double altura_Dese_el_suelo) {
        Altura_Dese_el_suelo = altura_Dese_el_suelo;
    }
}

