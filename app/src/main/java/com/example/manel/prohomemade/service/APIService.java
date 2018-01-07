package com.example.manel.prohomemade.service;

import com.example.manel.prohomemade.model.ListPays;
import com.example.manel.prohomemade.model.Pays;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by manel on 24/12/2017.
 */

public interface APIService {

    //change the url accoding to your system IP Address
    //invite de commande -> ipconfig
    //wifi section adresse vpI4
    String dbURL="http://192.168.1.100/proHMcrud/";

    @GET("view.php")
    Call<ListPays> getAllPays();
}
