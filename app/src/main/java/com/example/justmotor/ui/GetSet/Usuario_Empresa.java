package com.example.justmotor.ui.GetSet;

public class Usuario_Empresa {

    private int Id_Usuario_Concesionario;
    private int Concesionario;
    private int Oferta;
    private String Contraseña;
    private String Gmail_Con;
    private String Telefono_Con;

    //Constructor
    public Usuario_Empresa(int id_Usuario_Concesionario, int concesionario, int oferta, String contraseña, String gmail_Con, String telefono_Con) {
        this.Id_Usuario_Concesionario = id_Usuario_Concesionario;
        this.Concesionario = concesionario;
        this.Oferta = oferta;
        this.Contraseña = contraseña;
        this.Gmail_Con = gmail_Con;
        this.Telefono_Con = telefono_Con;
    }

    //Getters i setters
    public int getId_Usuario_Concesionario() {
        return Id_Usuario_Concesionario;
    }

    public void setId_Usuario_Concesionario(int id_Usuario_Concesionario) {
        Id_Usuario_Concesionario = id_Usuario_Concesionario;
    }

    public int getConcesionario() {
        return Concesionario;
    }

    public void setConcesionario(int concesionario) {
        Concesionario = concesionario;
    }

    public int getOferta() {
        return Oferta;
    }

    public void setOferta(int oferta) {
        Oferta = oferta;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getGmail_Con() {
        return Gmail_Con;
    }

    public void setGmail_Con(String gmail_Con) {
        Gmail_Con = gmail_Con;
    }

    public String getTelefono_Con() {
        return Telefono_Con;
    }

    public void setTelefono_Con(String telefono_Con) {
        Telefono_Con = telefono_Con;
    }
}
