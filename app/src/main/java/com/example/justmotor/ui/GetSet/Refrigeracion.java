package com.example.justmotor.ui.GetSet;

public class Refrigeracion {

    private int Id_Refrigeracion;
    private boolean Liquida;
    private boolean Aire;


    //Constructor
    public Refrigeracion(int id_Refrigeracion, boolean liquida, boolean aire) {
        this.Id_Refrigeracion = id_Refrigeracion;
        this.Liquida = liquida;
        this.Aire = aire;
    }

    //getters i Setters
    public int getId_Refrigeracion() {
        return Id_Refrigeracion;
    }

    public void setId_Refrigeracion(int id_Refrigeracion) {
        Id_Refrigeracion = id_Refrigeracion;
    }

    public boolean getLiquida() {
        return Liquida;
    }

    public void setLiquida(boolean liquida) {
        Liquida = liquida;
    }

    public boolean getAire() {
        return Aire;
    }

    public void setAire(boolean aire) {
        Aire = aire;
    }
}
