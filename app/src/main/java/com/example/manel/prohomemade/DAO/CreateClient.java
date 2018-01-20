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
 * Created by manel on 13/01/2018.
 */

public class CreateClient {
    private boolean b;

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }
    public boolean createClient(String nom, String prenom, String email, String password, int tel, String dPay) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.dbURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<String> call = api.CreateClient(nom, prenom, email, tel, password, dPay);
        call.enqueue(new Callback<String>() {
            String kjj = "";
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ress = response.body();
                if (ress.matches("succe")) {
                    setB(true);
                    Log.d("create client succe", "valide Request");
                } else {
                    setB(false);
                    Log.d("failedsucce", "invalide Request");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                setB(true);
                Log.d("create client failed", "invalide Request " + t.getMessage());
            }
        });

        Log.d("ajouter client bbbbbb", String.valueOf(isB()));
        return isB();
    }
}
