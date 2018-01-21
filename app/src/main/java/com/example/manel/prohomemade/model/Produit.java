package com.example.manel.prohomemade.model;

/**
 * Created by manel on 21/01/2018.
 */

public class Produit {
    private int id;
    private int idArt;
    private String nom;
    private String descri;
    private String catg;
    private Float prix;
    private String dateProd;
    private String dispo;
    private String img;

    public Produit() {
    }

    public Produit(int id, int idArt, String nom, String descri, String catg, Float prix, String dateProd, String dispo, String img) {
        this.id = id;
        this.idArt = idArt;
        this.nom = nom;
        this.descri = descri;
        this.catg = catg;
        this.prix = prix;
        this.dateProd = dateProd;
        this.dispo = dispo;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdArt() {
        return idArt;
    }

    public void setIdArt(int idArt) {
        this.idArt = idArt;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public String getCatg() {
        return catg;
    }

    public void setCatg(String catg) {
        this.catg = catg;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDateProd() {
        return dateProd;
    }

    public void setDateProd(String dateProd) {
        this.dateProd = dateProd;
    }

    public String getDispo() {
        return dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
