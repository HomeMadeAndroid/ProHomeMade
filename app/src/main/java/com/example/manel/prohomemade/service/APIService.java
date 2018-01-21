package com.example.manel.prohomemade.service;

import com.example.manel.prohomemade.model.Artisant;
import com.example.manel.prohomemade.model.Client;
import com.example.manel.prohomemade.model.ListPays;
import com.example.manel.prohomemade.model.ListProduit;

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

    @GET("ListeProduit.php")
    Call<ListProduit> getAllProduit();

    @FormUrlEncoded
    @POST("getMyProduct.php")
    Call<ListProduit> getMyProduct(@Field("email") String email,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("CkeckArtisant.php")
    Call<Artisant> CheckArtisant(@Field("email") String email,
                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("CkeckClient.php")
    Call<Client> CheckClient(@Field("email") String email,
                             @Field("password") String password);

    @FormUrlEncoded
    @POST("CkeckArtisantFG.php")
    Call<Artisant> CheckArtisantFG(@Field("email") String email);

    @FormUrlEncoded
    @POST("CkeckClientFG.php")
    Call<Client> CheckClientFG(@Field("email") String email);

    @FormUrlEncoded
    @POST("insertClient.php")
    Call<String> CreateClient(@Field("nom") String nom,
                              @Field("prenom") String prenom,
                              @Field("email") String email,
                              @Field("tel") int tel,
                              @Field("password") String password,
                              @Field("dPay") String dPay);

    @FormUrlEncoded
    @POST("insertArtisant.php")
    Call<String> CreateArtisant(@Field("nom") String nom,
                                @Field("prenom") String prenom,
                                @Field("email") String email,
                                @Field("tel") int tel,
                                @Field("password") String password,
                                @Field("adr") String adr,
                                @Field("matfisc") String matfisc);

    @FormUrlEncoded
    @POST("ModifierArtisant.php")
    Call<String> ModifierArtisant(@Field("nom") String nom,
                                  @Field("prenom") String prenom,
                                  @Field("tel") int tel,
                                  @Field("matfisc") String matfisc,
                                  @Field("emailc") String emailc,
                                  @Field("passwordc") String passwordc);

    @FormUrlEncoded
    @POST("ModifierClient.php")
    Call<String> ModifierClient(@Field("nom") String nom,
                                @Field("prenom") String prenom,
                                @Field("tel") int tel,
                                @Field("emailc") String emailc,
                                @Field("passwordc") String passwordc);

    @FormUrlEncoded
    @POST("insertProduit.php")
    Call<String> Createproduct(@Field("nom") String nom,
                               @Field("descri") String descri,
                               @Field("catg") String catg,
                               @Field("prix") float prix,
                               @Field("dispo") String dispo,
                               @Field("nomart") String nomart,
                               @Field("email") String email);
}
