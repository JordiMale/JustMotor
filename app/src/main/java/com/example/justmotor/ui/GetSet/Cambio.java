package com.example.justmotor.ui.GetSet;

public class Cambio {

    private int Id_Cambio;
    private boolean Manual;
    private boolean Automatico;

    //Constructor
    public Cambio(int id_Cambio, boolean manual, boolean automatico) {
        this.Id_Cambio = id_Cambio;
        this.Manual = manual;
        this.Automatico = automatico;
    }

    //Getters i Setters
    public int getId_Cambio() {
        return Id_Cambio;
    }

    public void setId_Cambio(int id_Cambio) {
        Id_Cambio = id_Cambio;
    }

    public boolean getManual() {
        return Manual;
    }

    public void setManual(boolean manual) {
        Manual = manual;
    }

    public boolean getAutomatico() {
        return Automatico;
    }

    public void setAutomatico(boolean automatico) {
        Automatico = automatico;
    }
}
