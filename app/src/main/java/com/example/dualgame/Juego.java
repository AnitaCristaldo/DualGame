package com.example.dualgame;

// Juego.java
import java.util.List;

public class Juego {
    private String descripcion; // Descripción del juego
    private String imagen; // URL de la imagen del juego
    private List<String> opciones; // Opciones disponibles en el juego

    public Juego() {
        // Constructor vacío necesario para Firestore
    }

    public Juego(String descripcion, String imagen, List<String> opciones) {
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.opciones = opciones;
    }

    // Getters y Setters
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public List<String> getOpciones() { return opciones; }
    public void setOpciones(List<String> opciones) { this.opciones = opciones; }
}
