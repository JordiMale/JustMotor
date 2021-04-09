package com.example.justmotor.ui.GetSet;

import java.util.Date;

public class Ficha_Tecnica_Modelo {

    private int Id_Ficha_Tecnia;
    private int Motor;
    private int Neumaticos;
    private int Dimensiones;
    private String Matricula;
    private int Km;
    private String Precio;
    private String Fotos;
    private Date Año;
    private double Consumo;
    private String Marca_Frenos;
    private boolean ABS;
    private String Color;

    //Constructor
    public Ficha_Tecnica_Modelo(int id_Ficha_Tecnia, int motor, int neumaticos, int dimensiones, String matricula, int km, String precio, String fotos, Date año, double consumo, String marca_Frenos, boolean ABS, String color) {
        Id_Ficha_Tecnia = id_Ficha_Tecnia;
        Motor = motor;
        Neumaticos = neumaticos;
        Dimensiones = dimensiones;
        Matricula = matricula;
        Km = km;
        Precio = precio;
        Fotos = fotos;
        Año = año;
        Consumo = consumo;
        Marca_Frenos = marca_Frenos;
        this.ABS = ABS;
        Color = color;
    }

    //Getters i setters
    public int getId_Ficha_Tecnia() {
        return Id_Ficha_Tecnia;
    }

    public void setId_Ficha_Tecnia(int id_Ficha_Tecnia) {
        Id_Ficha_Tecnia = id_Ficha_Tecnia;
    }

    public int getMotor() {
        return Motor;
    }

    public void setMotor(int motor) {
        Motor = motor;
    }

    public int getNeumaticos() {
        return Neumaticos;
    }

    public void setNeumaticos(int neumaticos) {
        Neumaticos = neumaticos;
    }

    public int getDimensiones() {
        return Dimensiones;
    }

    public void setDimensiones(int dimensiones) {
        Dimensiones = dimensiones;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String matricula) {
        Matricula = matricula;
    }

    public int getKm() {
        return Km;
    }

    public void setKm(int km) {
        Km = km;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getFotos() {
        return Fotos;
    }

    public void setFotos(String fotos) {
        Fotos = fotos;
    }

    public Date getAño() {
        return Año;
    }

    public void setAño(Date año) {
        Año = año;
    }

    public double getConsumo() {
        return Consumo;
    }

    public void setConsumo(double consumo) {
        Consumo = consumo;
    }

    public String getMarca_Frenos() {
        return Marca_Frenos;
    }

    public void setMarca_Frenos(String marca_Frenos) {
        Marca_Frenos = marca_Frenos;
    }

    public boolean isABS() {
        return ABS;
    }

    public void setABS(boolean ABS) {
        this.ABS = ABS;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }
}
