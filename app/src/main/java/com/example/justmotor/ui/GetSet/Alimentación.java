package com.example.justmotor.ui.GetSet;

public class Alimentación {

    private int Id_Alimentacion;
    private boolean Gasolina;
    private boolean Electricidad;

    //Constructor
    public Alimentación(int id_Alimentacion, boolean gasolina, boolean electricidad) {
        this.Id_Alimentacion = id_Alimentacion;
        this.Gasolina = gasolina;
        this.Electricidad = electricidad;
    }

    //Getters i Setters
    public int getId_Alimentacion() {
        return Id_Alimentacion;
    }

    public void setId_Alimentacion(int id_Alimentacion) {
        Id_Alimentacion = id_Alimentacion;
    }

    public boolean getGasolina() {
        return Gasolina;
    }

    public void setGasolina(boolean gasolina) {
        Gasolina = gasolina;
    }

    public boolean getElectricidad() {
        return Electricidad;
    }

    public void setElectricidad(boolean electricidad) {
        Electricidad = electricidad;
    }
}
