package com.example.dualgame;

import java.util.Date;
import java.util.Date;
//Ptrueba 31/10
//segunda prueba 31/10
//tercera
public class Usuario {
    private String nombre;
    private int edad;
    private String email;

    public Usuario() {
        // Constructor vacío requerido para Firestore
    }

    public Usuario(String nombre, int edad, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;

    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Progreso getProgreso() {
        return null;
    }

}
