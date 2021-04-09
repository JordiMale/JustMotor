package com.example.justmotor.ui.GetSet;

public class Tipo_Motor {

    private int Id_Tipo_Motor;
    private int Tiempo;
    private int Numero_Cilindors;


    //Constructor
    public Tipo_Motor(int id_Tipo_Motor, int tiempo, int numero_Cilindors) {
        this.Id_Tipo_Motor = id_Tipo_Motor;
        this.Tiempo = tiempo;
        this.Numero_Cilindors = numero_Cilindors;
    }

    //Getters i Setters
    public int getId_Tipo_Motor() {
        return Id_Tipo_Motor;
    }

    public void setId_Tipo_Motor(int id_Tipo_Motor) {
        Id_Tipo_Motor = id_Tipo_Motor;
    }

    public int getTiempo() {
        return Tiempo;
    }

    public void setTiempo(int tiempo) {
        Tiempo = tiempo;
    }

    public int getNumero_Cilindors() {
        return Numero_Cilindors;
    }

    public void setNumero_Cilindors(int numero_Cilindors) {
        Numero_Cilindors = numero_Cilindors;
    }
}
