package com.example.manel.prohomemade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.manel.prohomemade.Adapter.RcvProduit;
import com.example.manel.prohomemade.model.ListProduit;
import com.example.manel.prohomemade.service.APIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListeProduitArt extends AppCompatActivity {

    RecyclerView recyclerView;
    String name, prename, matfisc;
    int tel;
    String email, password, account;
    private RcvProduit rcvProduit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_produit_art);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            name = (String) b.get("nom");
            prename = (String) b.get("prenom");
            tel = b.getInt("tel");
            email = (String) b.get("email");
            password = (String) b.get("password");
            matfisc = (String) b.get("matfisc");
            account = (String) b.get("account");
            Log.d("nameartisant a modifier", name);
            Log.d("prmartisant a modifier", prename);
            Log.d("emlartisant a modifier", email);
            Log.d("telartisant a modifier", String.valueOf(tel));
            Log.d("pswartisant a modifier", password);
            Log.d("matartisant a modifier", matfisc);
        }
        recyclerView = (RecyclerView) findViewById(R.id.rcvProduitl);
        LoadDataProduit();
    }

    public void LoadDataProduit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.dbURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<ListProduit> call = api.getMyProduct(email, password);
        call.enqueue(new Callback<ListProduit>() {
            @Override
            public void onResponse(Call<ListProduit> call, Response<ListProduit> response) {
                ListProduit listProduct = response.body();
                if (listProduct.getStatus() == 1) {
                    Log.d("erreur: ", listProduct.getListP().toString());
                    rcvProduit = new RcvProduit(getApplicationContext(), listProduct.getListP(), "art");
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(rcvProduit);
                } else {
                    Toast.makeText(getApplicationContext(), "you don't have products " + listProduct.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListProduit> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("erreur: ", t.getMessage());
            }
        });
    }

}
