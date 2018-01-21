package com.example.manel.prohomemade.service;

/**
 * Created by manel on 13/01/2018.
 */

public interface IGererClient {
    public boolean CreateClient(String nom,
                                String prenom,
                                String email,
                                int tel,
                                String password,
                                String dPay);

    public boolean Checkclient(String email, String password);

    public boolean ModifierClient(String nom,
                                  String prenom,
                                  int tel,
                                  String emailm,
                                  String passwordc);
}
