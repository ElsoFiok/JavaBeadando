package com.beadando.javabeadando;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Jelentkezes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int jelentkezoid;
    private int kepzesid;
    private int sorrend;
    private int szerzett;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJelentkezoid() {
        return jelentkezoid;
    }

    public void setJelentkezoid(int jelentkezoid) {
        this.jelentkezoid = jelentkezoid;
    }

    public int getKepzesid() {
        return kepzesid;
    }

    public void setKepzesid(int kepzesid) {
        this.kepzesid = kepzesid;
    }

    public int getSorrend() {
        return sorrend;
    }

    public void setSorrend(int sorrend) {
        this.sorrend = sorrend;
    }

    public int getSzerzett() {
        return szerzett;
    }

    public void setSzerzett(int szerzett) {
        this.szerzett = szerzett;
    }


}
