package com.example.manel.prohomemade.service;

import com.example.manel.prohomemade.DAO.CreateProduct;

/**
 * Created by manel on 21/01/2018.
 */

public class GererProduit implements IGererProduit {
    CreateProduct createProduct;

    @Override
    public boolean CreateProduct(String nom, String descri, String catg, float prix, String dispo, String nomart, String email) {
        if (this.createProduct.createproduct(nom, descri, catg, prix, dispo, nomart, email)) {
            return true;
        } else {
            return false;
        }
    }

}