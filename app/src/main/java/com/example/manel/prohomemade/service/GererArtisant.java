package com.example.manel.prohomemade.service;

import com.example.manel.prohomemade.DAO.CreateArtisant;

/**
 * Created by manel on 18/01/2018.
 */

public class GererArtisant implements IGererArtisant {
    private CreateArtisant createArtisant;

    public GererArtisant(CreateArtisant createArtisant) {
        this.createArtisant = createArtisant;
    }

    @Override
    public boolean CreateArtisant(String nom, String prenom, String email, int tel, String password, String adr, String matfisc) {
        if (this.createArtisant.createArtisant(nom, prenom, email, tel, password, adr, matfisc)) {
            return true;
        } else {
            return false;
        }
    }
}
