package com.example.manel.prohomemade.model;

import java.util.List;

/**
 * Created by manel on 13/01/2018.
 */

public class ListClient {
    private List<Client> listClient;
    private String msg;
    private int status;

    public ListClient() {
    }

    public ListClient(List<Client> listClient, String msg, int status) {
        this.listClient = listClient;
        this.msg = msg;
        this.status = status;
    }

    public List<Client> getListClient() {
        return listClient;
    }

    public void setListClient(List<Client> listClient) {
        this.listClient = listClient;
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
