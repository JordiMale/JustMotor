package com.example.justmotor.ui.GetSet;

public class Favorios {

    private int Id_Favoritos;
    private int Usuario;
    private int Oferta;

    //Constructor
    public Favorios(int id_Favoritos, int usuario, int oferta) {
        Id_Favoritos = id_Favoritos;
        Usuario = usuario;
        Oferta = oferta;
    }

    //Getters i setters
    public int getId_Favoritos() {
        return Id_Favoritos;
    }

    public void setId_Favoritos(int id_Favoritos) {
        Id_Favoritos = id_Favoritos;
    }

    public int getUsuario() {
        return Usuario;
    }

    public void setUsuario(int usuario) {
        Usuario = usuario;
    }

    public int getOferta() {
        return Oferta;
    }

    public void setOferta(int oferta) {
        Oferta = oferta;
    }
}
