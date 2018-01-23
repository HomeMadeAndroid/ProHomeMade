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

public class SupprimerProduit {
    private boolean b = true;

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public boolean deleteProduit(int idProd) {

        Log.d("idprod pproduit", "" + idProd);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.dbURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<String> call = api.DeleteProduit(idProd);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ress = response.body();
                Log.d("ressss suppproduit", ress);
                if (ress.matches("supprimer")) {
                    setB(true);
                    Log.d("supprimer produit succe", "valide Request");
                } else {
                    setB(false);
                    Log.d("failedsucce", "invalide Request");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                setB(false);
                Log.d("supprimer prod failed", "invalide Request " + t.getMessage());
            }
        });

        Log.d("supprimer produit", String.valueOf(isB()));
        return isB();
    }
}
