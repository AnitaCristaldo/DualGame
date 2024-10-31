package com.example.dualgame;

public class Modulo {
    private Nivel nivelVocales;
    private Nivel nivelAbecedario;
    private Nivel nivelNumeros;  // Nuevo campo para el nivel de números

    // Constructor vacío necesario para Firestore
    public Modulo() {
    }

    // Getters y Setters para nivelVocales
    public Nivel getNivelVocales() {
        return nivelVocales;
    }

    public void setNivelVocales(Nivel nivelVocales) {
        this.nivelVocales = nivelVocales;
    }

    // Getters y Setters para nivelAbecedario
    public Nivel getNivelAbecedario() {
        return nivelAbecedario;
    }

    public void setNivelAbecedario(Nivel nivelAbecedario) {
        this.nivelAbecedario = nivelAbecedario;
    }

    // Getters y Setters para nivelNumeros
    public Nivel getNivelNumeros() {
        return nivelNumeros;
    }

    public void setNivelNumeros(Nivel nivelNumeros) {
        this.nivelNumeros = nivelNumeros;
    }
}
