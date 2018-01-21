package com.example.manel.prohomemade.service;

/**
 * Created by manel on 18/01/2018.
 */

public interface IGererArtisant {
    public boolean CreateArtisant(String nom,
                                  String prenom,
                                  String email,
                                  int tel,
                                  String password,
                                  String adr,
                                  String matfisc);

    public boolean ModifierArtisant(String nom,
                                    String prenom,
                                    int tel,
                                    String matfisc,
                                    String emailm,
                                    String passwordc);
}
