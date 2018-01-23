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
 * Created by manel on 23/01/2018.
 */

public class ModifierProduit {
    private boolean b = true;

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public boolean modifierProduit(int idProd, String nom, String descri, float prix, String catg, String dispo) {

        Log.d("idprod pproduit", "" + idProd);
        Log.d("nom pproduit", nom);
        Log.d("descri pproduit", descri);
        Log.d("prix pproduit", "" + prix);
        Log.d("catg pproduit", catg);
        Log.d("dispo pproduit", dispo);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.dbURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<String> call = api.ModifierProduit(nom, idProd, descri, catg, prix, dispo);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ress = response.body();
                Log.d("ressss modproduit", ress);
                if (ress.matches("modified")) {
                    setB(true);
                    Log.d("modifier produit succe", "valide Request");
                } else {
                    setB(false);
                    Log.d("failedsucce", "invalide Request");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                setB(false);
                Log.d("modifier produit failed", "invalide Request " + t.getMessage());
            }
        });

        Log.d("modifier produit", String.valueOf(isB()));
        return isB();
    }
}
