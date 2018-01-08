package com.example.manel.prohomemade.model;

/**
 * Created by manel on 24/12/2017.
 */

public class Pays {

    private int id;
    private String design;

    public Pays(int id, String design) {
        this.id = id;
        this.design = design;
    }

    public Pays() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }
}
