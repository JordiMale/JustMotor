package com.example.justmotor.ui.GetSet;

public class Dimension_Neumatico {

    private int Dimension_Neumatico;
    private int Ancho;
    private int Perfil;
    private int Radio;

    //Constructor
    public Dimension_Neumatico(int dimension_Neumatico, int ancho, int perfil, int radio) {
        Dimension_Neumatico = dimension_Neumatico;
        Ancho = ancho;
        Perfil = perfil;
        Radio = radio;
    }

    //Getters i Setters
    public int getDimension_Neumatico() {
        return Dimension_Neumatico;
    }

    public void setDimension_Neumatico(int dimension_Neumatico) {
        Dimension_Neumatico = dimension_Neumatico;
    }

    public int getAncho() {
        return Ancho;
    }

    public void setAncho(int ancho) {
        Ancho = ancho;
    }

    public int getPerfil() {
        return Perfil;
    }

    public void setPerfil(int perfil) {
        Perfil = perfil;
    }

    public int getRadio() {
        return Radio;
    }

    public void setRadio(int radio) {
        Radio = radio;
    }
}
