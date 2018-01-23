package com.example.manel.prohomemade.model;

import java.util.List;

/**
 * Created by manel on 23/01/2018.
 */

public class LigneCmd {
    List<Cmd> listCmd;
    List<Produit> listP;
    private String msg;
    private int status;

    public LigneCmd() {
    }

    public LigneCmd(List<Cmd> listCmd, List<Produit> listP, String msg, int status) {
        this.listCmd = listCmd;
        this.listP = listP;
        this.msg = msg;
        this.status = status;
    }

    public List<Cmd> getListCmd() {
        return listCmd;
    }

    public void setListCmd(List<Cmd> listCmd) {
        this.listCmd = listCmd;
    }

    public List<Produit> getListP() {
        return listP;
    }

    public void setListP(List<Produit> listP) {
        this.listP = listP;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
