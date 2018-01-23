package com.example.manel.prohomemade.model;

/**
 * Created by manel on 23/01/2018.
 */

public class Cmd {
    int tel;
    String nom;
    String nomP;

    public Cmd() {
    }

    public Cmd(int tel, String nom, String nomP) {
        this.tel = tel;
        this.nom = nom;
        this.nomP = nomP;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }
}
