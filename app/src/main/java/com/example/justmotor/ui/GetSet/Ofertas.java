package com.example.justmotor.ui.GetSet;

import java.util.Date;

public class Ofertas {

    private int Id_Oferta;
    private String Precio;
    private String Foto;
    private String Matricula;
    private String Marca;
    private String Modelo;
    private String Data_Entrada;
    private String Data_Final;
    private boolean activa;

    //Constructor
    public Ofertas(int id_Oferta, String precio,String matricula, String marca, String foto, String modelo, String data_Entrada, String data_Final, boolean activa) {
        this.Id_Oferta = id_Oferta;
        this.Precio = precio;
        this.Foto = foto;
        this.Matricula = matricula;
        this.Marca = marca;
        this.Modelo = modelo;
        this.Data_Entrada = data_Entrada;
        this.Data_Final = data_Final;
        this.activa = activa;
    }

    public String toString(){
        return "\tId_Oferta: " + Id_Oferta + '\n' +
                "\tPrecio: " + Precio + '\n' +
                "\tFoto: " + Foto + '\n' +
                "\tMatricula: " + Matricula + '\n'+
                "\tMarca: " + Marca + '\n' +
                "\tModelo: " + Modelo + '\n' +
                "\tData_Entrada: " + Data_Entrada + '\n' +
                "\tData_Final: " + Data_Final + '\n' +
                "\tactiva: " + activa + '\n';
    }

    //Getters i setters
    public int getId_Oferta() {
        return Id_Oferta;
    }

    public void setId_Oferta(int id_Oferta) {
        Id_Oferta = id_Oferta;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String matricula) {
        Matricula = matricula;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }


    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getData_Entrada() {
        return Data_Entrada;
    }

    public void setData_Entrada(String data_Entrada) {
        Data_Entrada = data_Entrada;
    }

    public String getData_Final() {
        return Data_Final;
    }

    public void setData_Final(String data_Final) {
        Data_Final = data_Final;
    }

    public boolean getActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
