package com.example.manel.prohomemade.service;

/**
 * Created by manel on 21/01/2018.
 */

public interface IGererProduit {
    boolean CreateProduct(String nom,
                          String descri,
                          String catg,
                          float prix,
                          String dispo,
                          String nomart,
                          String email);
}
