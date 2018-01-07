package com.example.manel.prohomemade.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by manel on 24/12/2017.
 */

public class Pays {

    private String id;
    private String design;

    public Pays(String id, String design) {
        this.id = id;
        this.design = design;
    }

    public Pays() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }
}
