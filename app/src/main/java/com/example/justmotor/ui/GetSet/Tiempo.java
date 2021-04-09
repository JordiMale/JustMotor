package com.example.justmotor.ui.GetSet;

public class Tiempo {

    private int Id_Tiempo;
    private boolean QuatroT;
    private boolean DosT;

    //Constructor
    public Tiempo(int id_Tiempo, boolean quatroT, boolean dosT) {
        this.Id_Tiempo = id_Tiempo;
        this.QuatroT = quatroT;
        this.DosT = dosT;
    }

    //Getters i Setters
    public int getId_Tiempo() {
        return Id_Tiempo;
    }

    public void setId_Tiempo(int id_Tiempo) {
        Id_Tiempo = id_Tiempo;
    }

    public boolean getQuatroT() {
        return QuatroT;
    }

    public void setQuatroT(boolean quatroT) {
        QuatroT = quatroT;
    }

    public boolean getDosT() {
        return DosT;
    }

    public void setDosT(boolean dosT) {
        DosT = dosT;
    }
}
