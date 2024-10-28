package com.beadando.javabeadando;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "uzenet", nullable = false, length = 500)
    private String uzenet;

    @Column(name = "kuldoid", nullable = false)
    private int kuldoid;

    @Column(name = "ido", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp ido;

    @ManyToOne
    @JoinColumn(name = "kuldoid", referencedColumnName = "id", insertable = false, updatable = false)
    private User sender; // Assuming you have a User class

    // Getters and setters...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUzenet() {
        return uzenet;
    }

    public void setUzenet(String uzenet) {
        this.uzenet = uzenet;
    }

    public int getKuldoid() {
        return kuldoid;
    }

    public void setKuldoid(int kuldoid) {
        this.kuldoid = kuldoid;
    }

    public Timestamp getIdo() {
        return ido;
    }

    public void setIdo(Timestamp ido) {
        this.ido = ido;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
