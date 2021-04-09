package com.example.justmotor.ui.GetSet;

import java.util.Date;

public class Ofertas {

    private int Id_Oferta;
    private String Usuario_Concesionario;
    private String Modelo;
    private Date Data_Entrada;
    private Date Data_Final;
    private boolean activa;

    //Constructor
    public Ofertas(int id_Oferta, String usuario_Concesionario, String modelo, Date data_Entrada, Date data_Final, boolean activa) {
        this.Id_Oferta = id_Oferta;
        this.Usuario_Concesionario = usuario_Concesionario;
        this.Modelo = modelo;
        this.Data_Entrada = data_Entrada;
        this.Data_Final = data_Final;
        this.activa = activa;
    }

    //Getters i setters
    public int getId_Oferta() {
        return Id_Oferta;
    }

    public void setId_Oferta(int id_Oferta) {
        Id_Oferta = id_Oferta;
    }

    public String getUsuario_Concesionario() {
        return Usuario_Concesionario;
    }

    public void setUsuario_Concesionario(String usuario_Concesionario) {
        Usuario_Concesionario = usuario_Concesionario;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public Date getData_Entrada() {
        return Data_Entrada;
    }

    public void setData_Entrada(Date data_Entrada) {
        Data_Entrada = data_Entrada;
    }

    public Date getData_Final() {
        return Data_Final;
    }

    public void setData_Final(Date data_Final) {
        Data_Final = data_Final;
    }

    public boolean getActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
