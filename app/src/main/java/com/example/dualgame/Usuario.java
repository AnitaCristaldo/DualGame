package com.example.dualgame;
import com.google.firebase.Timestamp;
import java.util.Date;
import java.util.Date;
public class Usuario {
    private String nombre;
    private int edad;
    private String email;
    private Timestamp fechaRegistro;
    public Usuario() {
        // Constructor vacío requerido para Firestore
    }
    public Usuario(String nombre, int edad, String email, Timestamp fechaRegistro) {
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
        this.fechaRegistro = fechaRegistro;

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
    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
