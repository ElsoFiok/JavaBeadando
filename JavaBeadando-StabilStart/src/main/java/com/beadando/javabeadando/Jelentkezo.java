package com.beadando.javabeadando;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Jelentkezo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nev;
    char nem;

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

    public char getNem() {
        return nem;
    }

    public void setNem(char nem) {
        this.nem = nem;
    }

}
