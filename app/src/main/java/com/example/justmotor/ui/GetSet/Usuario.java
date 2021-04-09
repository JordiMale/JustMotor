package com.example.justmotor.ui.GetSet;

public class Usuario {

    private int id;
    private String Nombre;
    private String Apellido1;
    private String Apellido2;
    private int Contacte_Empresa;
    private int Favoritos;
    private String Dni;
    private String Contraseña;
    private String Gmail;
    private int Telefono;

    public Usuario(int id, String nombre, String apellido1, String apellido2, int contacte_Empresa, int favoritos, String dni, String contraseña, String gmail, int telefono) {
        this.id = id;
        this.Nombre = nombre;
        this.Apellido1 = apellido1;
        this.Apellido2 = apellido2;
        this.Contacte_Empresa = contacte_Empresa;
        this.Favoritos = favoritos;
        this.Dni = dni;
        this.Contraseña = contraseña;
        this.Gmail = gmail;
        this.Telefono = telefono;
    }

    //Getters i setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido1() {
        return Apellido1;
    }

    public void setApellido1(String apellido1) {
        Apellido1 = apellido1;
    }

    public String getApellido2() {
        return Apellido2;
    }

    public void setApellido2(String apellido2) {
        Apellido2 = apellido2;
    }

    public int getContacte_Empresa() {
        return Contacte_Empresa;
    }

    public void setContacte_Empresa(int contacte_Empresa) {
        Contacte_Empresa = contacte_Empresa;
    }

    public int getFavoritos() {
        return Favoritos;
    }

    public void setFavoritos(int favoritos) {
        Favoritos = favoritos;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int telefono) {
        Telefono = telefono;
    }




}
