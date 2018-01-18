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
 * Created by manel on 18/01/2018.
 */

public class CreateArtisant {
    private boolean b = true;

    public boolean createArtisant(String nom, String prenom, String email, int tel, String password,
                                  String adr, String matfisc) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.dbURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<String> call = api.CreateArtisant(nom, prenom, email, tel, password, adr, matfisc);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    setB(true);
                    Log.d("create artisant succe", "valide Request");
                }
                setB(true);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                setB(false);
                Log.d("create artisant failed", "invalide Request " + t.getMessage());
            }
        });

        Log.d("ajouter artisant", String.valueOf(isB()));
        return isB();
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }
}
