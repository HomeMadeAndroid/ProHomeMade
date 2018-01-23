package com.example.manel.prohomemade.model;

import java.util.List;

/**
 * Created by manel on 23/01/2018.
 */

public class Consult {
    List<Cmd> lconsulter;
    String msg;
    int satut;

    public Consult() {
    }

    public Consult(List<Cmd> lconsulter, String msg, int satut) {
        this.lconsulter = lconsulter;
        this.msg = msg;
        this.satut = satut;
    }

    public List<Cmd> getLconsulter() {
        return lconsulter;
    }

    public void setLconsulter(List<Cmd> lconsulter) {
        this.lconsulter = lconsulter;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getSatut() {
        return satut;
    }

    public void setSatut(int satut) {
        this.satut = satut;
    }
}
