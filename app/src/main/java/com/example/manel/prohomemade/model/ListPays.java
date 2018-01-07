package com.example.manel.prohomemade.model;

import java.util.List;

/**
 * Created by manel on 26/12/2017.
 */

public class ListPays {
    private List<Pays> listP;
    String msg;
    int status;

    public ListPays(List<Pays> listP, String msg, int status) {
        this.listP = listP;
        this.msg = msg;
        this.status = status;
    }

    public ListPays() {
    }

    public List<Pays> getListP() {
        return listP;
    }

    public void setListP(List<Pays> listP) {
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
