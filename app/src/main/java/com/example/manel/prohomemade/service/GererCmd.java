package com.example.manel.prohomemade.service;

import com.example.manel.prohomemade.DAO.CreateCmd;

/**
 * Created by manel on 22/01/2018.
 */

public class GererCmd implements IGererCmd {

    private CreateCmd createCmd;

    public GererCmd(CreateCmd createCmd) {
        this.createCmd = new CreateCmd();
    }

    @Override
    public boolean CreateCmd(String email, String password, int idProd, int qte) {
        if (this.createCmd.createCmd(email, password, idProd, qte)) {
            return true;
        } else {
            return false;
        }
    }
}
