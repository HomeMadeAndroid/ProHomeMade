package com.example.manel.prohomemade.service;

import com.example.manel.prohomemade.model.Client;
import com.example.manel.prohomemade.model.ListPays;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by manel on 24/12/2017.
 */

public interface APIService {

    //change the url accoding to your system IP Address
    //invite de commande -> ipconfig
    //wifi section adresse vpI4
    String dbURL = "http://192.168.1.100/proHMcrud/";

    @GET("view.php")
    Call<ListPays> getAllPays();

    @FormUrlEncoded
    @POST("insertClient.php")
    Call<Client> CreateClient(@Field("nom") String nom,
                              @Field("prenom") String prenom,
                              @Field("email") String email,
                              @Field("tel") int tel,
                              @Field("password") String password,
                              @Field("dPay") String dPay);
}
