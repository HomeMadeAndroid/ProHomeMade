package com.example.manel.prohomemade.service;

import com.example.manel.prohomemade.DAO.CreateArtisant;
import com.example.manel.prohomemade.DAO.ModifierArtisant;

/**
 * Created by manel on 18/01/2018.
 */

public class GererArtisant implements IGererArtisant {
    private CreateArtisant createArtisant;
    private ModifierArtisant modifierArtisant;

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

    @Override
    public boolean ModifierArtisant(String nom, String prenom, int tel, String matfisc, String emailm, String passwordc) {
        if (this.modifierArtisant.modifierArtisant(nom, prenom, tel, matfisc, emailm, passwordc)) {
            return true;
        } else {
            return false;
        }
    }
}
