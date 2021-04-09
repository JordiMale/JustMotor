package com.example.justmotor.ui.GetSet;

public class Encendido {

    private int Id_Encendido;
    private boolean Electronico;
    private boolean Manual;

    //Constructor
    public Encendido(int id_Encendido, boolean electronico, boolean manual) {
        this.Id_Encendido = id_Encendido;
        this.Electronico = electronico;
        this.Manual = manual;
    }

    //Getters i Setters
    public int getId_Encendido() {
        return Id_Encendido;
    }

    public void setId_Encendido(int id_Encendido) {
        Id_Encendido = id_Encendido;
    }

    public boolean getElectronico() {
        return Electronico;
    }

    public void setElectronico(boolean electronico) {
        Electronico = electronico;
    }

    public boolean getManual() {
        return Manual;
    }

    public void setManual(boolean manual) {
        Manual = manual;
    }
}
