package com.example.manel.prohomemade.DAO;

import android.util.Log;

import com.example.manel.prohomemade.service.APIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by manel on 21/01/2018.
 */

public class CreateProduct {
    private boolean b = true;

    public boolean createproduct(String nom, String descri, String catg, float prix, String dispo, String nomart, String email) {
        Log.d("detprod", nom);
        Log.d("detprod", descri);
        Log.d("detprod", catg);
        Log.d("detprod", "" + prix);
        Log.d("detprod", dispo);
        Log.d("detprod", nomart);
        Log.d("detprod", email);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.dbURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<String> call = api.Createproduct(nom, descri, catg, prix, dispo, nomart, email);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ress = response.body();
                Log.d("create Product succe", "valide Request " + ress);
                if (response.isSuccessful()) {
                    setB(true);
                    Log.d("create Product succe", "valide Request");

                }
                setB(true);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                setB(false);
                Log.d("create Product failed", "invalide Request " + t.getMessage());
            }
        });

        Log.d("ajouter produit", String.valueOf(isB()));
        return isB();
    }

    public boolean isB() {
        return b;
    }


    public void setB(boolean b) {
        this.b = b;
    }
}