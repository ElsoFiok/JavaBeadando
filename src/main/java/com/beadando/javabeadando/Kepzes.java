package com.beadando.javabeadando;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Kepzes{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nev;
    int felveheto;
    int minimum;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getFelveheto() {
        return felveheto;
    }

    public void setFelveheto(int felveheto) {
        this.felveheto = felveheto;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

}
