package com.example.justmotor.ui.GetSet;

public class Numero_Cilindors {

    private int Id_Numero_Cilindors;
    private boolean Uno;
    private boolean Dos;
    private boolean Tres;
    private boolean Cuatro;
    private boolean Seis;

    //Constructor
    public Numero_Cilindors(int id_Numero_Cilindors, boolean uno, boolean dos, boolean tres, boolean cuatro, boolean seis) {
        this.Id_Numero_Cilindors = id_Numero_Cilindors;
        this.Uno = uno;
        this.Dos = dos;
        this.Tres = tres;
        this.Cuatro = cuatro;
        this.Seis = seis;
    }

    //Getters i Setters
    public int getId_Numero_Cilindors() {
        return Id_Numero_Cilindors;
    }

    public void setId_Numero_Cilindors(int id_Numero_Cilindors) {
        Id_Numero_Cilindors = id_Numero_Cilindors;
    }

    public boolean getUno() {
        return Uno;
    }

    public void setUno(boolean uno) {
        Uno = uno;
    }

    public boolean getDos() {
        return Dos;
    }

    public void setDos(boolean dos) {
        Dos = dos;
    }

    public boolean getTres() {
        return Tres;
    }

    public void setTres(boolean tres) {
        Tres = tres;
    }

    public boolean getCuatro() {
        return Cuatro;
    }

    public void setCuatro(boolean cuatro) {
        Cuatro = cuatro;
    }

    public boolean getSeis() {
        return Seis;
    }

    public void setSeis(boolean seis) {
        Seis = seis;
    }
}
