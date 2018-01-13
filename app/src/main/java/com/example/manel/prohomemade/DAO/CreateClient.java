package com.example.manel.prohomemade.DAO;

import android.util.Log;

import com.example.manel.prohomemade.model.Client;
import com.example.manel.prohomemade.service.APIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by manel on 13/01/2018.
 */

public class CreateClient {
    private boolean b;

    public boolean createClient(String nom, String prenom, String email, String password, int tel, String dPay) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.dbURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<Client> call = api.CreateClient(nom, prenom, email, tel, password, dPay);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response.isSuccessful()) {
                    Log.d("create client succe", "valide Request");
                }
                setB(true);
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                setB(false);
                Log.d("create client failed", "invalide Request " + t.getMessage());
            }
        });

        Log.d("deposer offre", String.valueOf(isB()));
        return isB();
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }
}
