package com.example.justmotor.ui.GetSet;

public class Dimensiones_Moto {

    private int Id_Dimencsiones;
    private double Longitud_Total;
    private double Altura_Total;
    private double Distancia_Entre_Ejes;
    private double Altura_Desde_El_Suelo;
    private double Deposito_De_Gasolina;
    private double Peso;

    //Constructor
    public Dimensiones_Moto(int id_Dimencsiones, double longitud_Total, double altura_Total, double distancia_Entre_Ejes, double altura_Desde_El_Suelo, double deposito_De_Gasolina, double peso) {
        this.Id_Dimencsiones = id_Dimencsiones;
        this.Longitud_Total = longitud_Total;
        this.Altura_Total = altura_Total;
        this.Distancia_Entre_Ejes = distancia_Entre_Ejes;
        this.Altura_Desde_El_Suelo = altura_Desde_El_Suelo;
        this.Deposito_De_Gasolina = deposito_De_Gasolina;
        this.Peso = peso;
    }

    //Getters i Setters
    public int getId_Dimencsiones() {
        return Id_Dimencsiones;
    }

    public void setId_Dimencsiones(int id_Dimencsiones) {
        Id_Dimencsiones = id_Dimencsiones;
    }

    public double getLongitud_Total() {
        return Longitud_Total;
    }

    public void setLongitud_Total(double longitud_Total) {
        Longitud_Total = longitud_Total;
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

    public double getAltura_Desde_El_Suelo() {
        return Altura_Desde_El_Suelo;
    }

    public void setAltura_Desde_El_Suelo(double altura_Desde_El_Suelo) {
        Altura_Desde_El_Suelo = altura_Desde_El_Suelo;
    }

    public double getDeposito_De_Gasolina() {
        return Deposito_De_Gasolina;
    }

    public void setDeposito_De_Gasolina(double deposito_De_Gasolina) {
        Deposito_De_Gasolina = deposito_De_Gasolina;
    }

    public double getPeso() {
        return Peso;
    }

    public void setPeso(double peso) {
        Peso = peso;
    }
}
