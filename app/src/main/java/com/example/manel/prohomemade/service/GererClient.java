package com.example.manel.prohomemade.service;

import com.example.manel.prohomemade.DAO.CreateClient;
import com.example.manel.prohomemade.DAO.ModifierClient;

/**
 * Created by manel on 13/01/2018.
 */

public class GererClient implements IGererClient {

    private CreateClient createClient;
    private ModifierClient modClient;

    public GererClient() {
        this.createClient = new CreateClient();
    }

    @Override
    public boolean CreateClient(String nom, String prenom, String email, int tel, String password, String dPay) {
        if (this.createClient.createClient(nom, prenom, email, password, tel, dPay)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean Checkclient(String email, String password) {
        return false;
    }

    @Override
    public boolean ModifierClient(String nom, String prenom, int tel, String emailm, String passwordc) {
        if (this.modClient.modifierClient(nom, prenom, tel, emailm, passwordc)) {
            return true;
        } else {
            return false;
        }
    }
}
