package com.example.manel.prohomemade.model;

/**
 * Created by manel on 13/01/2018.
 */

public class Client {
    String nom;
    String prenom;
    String email;
    int tel;
    String password;
    String dP;

    public Client() {
    }

    public Client(String nom, String prenom, String email, int tel, String password, String dP) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.password = password;
        this.dP = dP;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdP() {
        return dP;
    }

    public void setIdP(String idP) {
        this.dP = idP;
    }
}
