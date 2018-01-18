package com.example.manel.prohomemade.model;

/**
 * Created by manel on 18/01/2018.
 */

public class Artisant {
    String nom;
    String prenom;
    String email;
    int tel;
    String password;
    String adr;
    String matfisc;

    public Artisant() {

    }

    public Artisant(String nom, String prenom, String email, int tel, String password, String adr, String matfisc) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.password = password;
        this.adr = adr;
        this.matfisc = matfisc;
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

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public String getMatfisc() {
        return matfisc;
    }

    public void setMatfisc(String matfisc) {
        this.matfisc = matfisc;
    }
}
