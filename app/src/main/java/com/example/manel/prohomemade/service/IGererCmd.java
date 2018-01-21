package com.example.manel.prohomemade.service;

/**
 * Created by manel on 22/01/2018.
 */

public interface IGererCmd {
    boolean CreateCmd(String email,
                      String password,
                      int idProd,
                      int qte);
}
