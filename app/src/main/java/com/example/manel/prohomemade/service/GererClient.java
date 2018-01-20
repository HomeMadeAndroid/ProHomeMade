package com.example.manel.prohomemade.service;

import com.example.manel.prohomemade.DAO.CreateClient;

/**
 * Created by manel on 13/01/2018.
 */

public class GererClient implements IGererClient {

    private CreateClient createClient;

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
}
