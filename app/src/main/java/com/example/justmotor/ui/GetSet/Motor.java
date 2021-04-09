package com.example.justmotor.ui.GetSet;

public class Motor {
    private int Id_Motor;
    private int Tipo_Motor;
    private int Refrigeracion;
    private int Encendido;
    private int Cambio;
    private int Alimentacion;
    private int Potencia;
    private double Cilindrada;
    private int Relacion_De_Compresion;
    private String Capacidad_De_Aceite;


    //Constructor
    public Motor(int id_Motor, int tipo_Motor, int refrigeracion, int encendido, int cambio, int alimentacion, int potencia, double cilindrada, int relacion_De_Compresion, String capacidad_De_Aceite) {
        Id_Motor = id_Motor;
        Tipo_Motor = tipo_Motor;
        Refrigeracion = refrigeracion;
        Encendido = encendido;
        Cambio = cambio;
        Alimentacion = alimentacion;
        Potencia = potencia;
        Cilindrada = cilindrada;
        Relacion_De_Compresion = relacion_De_Compresion;
        Capacidad_De_Aceite = capacidad_De_Aceite;
    }

    //Getters i setters
    public int getId_Motor() {
        return Id_Motor;
    }

    public void setId_Motor(int id_Motor) {
        Id_Motor = id_Motor;
    }

    public int getTipo_Motor() {
        return Tipo_Motor;
    }

    public void setTipo_Motor(int tipo_Motor) {
        Tipo_Motor = tipo_Motor;
    }

    public int getRefrigeracion() {
        return Refrigeracion;
    }

    public void setRefrigeracion(int refrigeracion) {
        Refrigeracion = refrigeracion;
    }

    public int getEncendido() {
        return Encendido;
    }

    public void setEncendido(int encendido) {
        Encendido = encendido;
    }

    public int getCambio() {
        return Cambio;
    }

    public void setCambio(int cambio) {
        Cambio = cambio;
    }

    public int getAlimentacion() {
        return Alimentacion;
    }

    public void setAlimentacion(int alimentacion) {
        Alimentacion = alimentacion;
    }

    public int getPotencia() {
        return Potencia;
    }

    public void setPotencia(int potencia) {
        Potencia = potencia;
    }

    public double getCilindrada() {
        return Cilindrada;
    }

    public void setCilindrada(double cilindrada) {
        Cilindrada = cilindrada;
    }

    public int getRelacion_De_Compresion() {
        return Relacion_De_Compresion;
    }

    public void setRelacion_De_Compresion(int relacion_De_Compresion) {
        Relacion_De_Compresion = relacion_De_Compresion;
    }

    public String getCapacidad_De_Aceite() {
        return Capacidad_De_Aceite;
    }

    public void setCapacidad_De_Aceite(String capacidad_De_Aceite) {
        Capacidad_De_Aceite = capacidad_De_Aceite;
    }
}
