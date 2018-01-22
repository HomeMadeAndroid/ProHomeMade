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
 * Created by manel on 22/01/2018.
 */

public class CreateCmd {
    private boolean b = true;

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public boolean createCmd(final String email, final String password, final int idProd, final int qte) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.dbURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<String> call = api.CreateCmd(email, password, idProd, qte);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ress = response.body();
                Log.d("ressssssss", ress);
                Log.d("resssssssse", email);
                Log.d("ressssssssp", password);
                Log.d("ressssssssi", "" + idProd);
                Log.d("ressssssssq", "" + qte);
                if (ress.matches("lignecmd")) {
                    setB(true);
                    Log.d("create cmd succe", "valide Request");
                } else {
                    setB(false);
                    Log.d("failedsucce", "invalide Request");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                setB(false);
                Log.d("create cmd failed", "invalide Request " + t.getMessage());
            }
        });

        Log.d("ajouter cmd bbbbbb", String.valueOf(isB()));
        return isB();
    }
}
