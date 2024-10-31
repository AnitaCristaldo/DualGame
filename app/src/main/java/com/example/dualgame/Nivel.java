package com.example.dualgame;



public class Nivel {
    private boolean completado;
    private int intentos;

    // Constructor vacío necesario para Firestore
    public Nivel() {
    }

    // Getters y Setters
    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }
}
