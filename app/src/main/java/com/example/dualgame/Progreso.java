package com.example.dualgame;
public class Progreso {
    private Modulo lenguaSenas;
    private Modulo braille;

    // Constructor vacío necesario para Firestore
    public Progreso() {
    }

    // Getters y Setters
    public Modulo getLenguaSenas() {
        return lenguaSenas;
    }

    public void setLenguaSenas(Modulo lenguaSenas) {
        this.lenguaSenas = lenguaSenas;
    }

    public Modulo getBraille() {
        return braille;
    }

    public void setBraille(Modulo braille) {
        this.braille = braille;
    }
}
