package com.example.manel.prohomemade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.manel.prohomemade.Adapter.RcvProduit2Client;
import com.example.manel.prohomemade.model.ListProduit;
import com.example.manel.prohomemade.service.APIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListeProduit extends AppCompatActivity {

    RecyclerView recyclerView;
    String nom, prenom, email, password, account;
    int tel;
    private RcvProduit2Client rcvProduit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_produit);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            nom = (String) b.get("nom");
            prenom = (String) b.get("prenom");
            tel = b.getInt("tel");
            email = (String) b.get("email");
            password = (String) b.get("password");
            account = (String) b.get("account");
            Log.d("nameclient a modifier", nom);
            Log.d("prenomclient a modifier", prenom);
            Log.d("emailclient a modifier", email);
            Log.d("telclient a modifier", String.valueOf(tel));
            Log.d("pswclient a modifier", password);
        }
        recyclerView = (RecyclerView) findViewById(R.id.rcvProduit);
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
        Call<ListProduit> call = api.getAllProduit();
        call.enqueue(new Callback<ListProduit>() {
            @Override
            public void onResponse(Call<ListProduit> call, Response<ListProduit> response) {
                ListProduit listProduct = response.body();
                if (listProduct.getStatus() == 1) {
                    Log.d("erreur: ", listProduct.getListP().toString());
                    Log.d("noooooom pp produitt", "" + nom);
                    rcvProduit = new RcvProduit2Client(getApplicationContext(), listProduct.getListP(), "client", nom,
                            prenom, tel, email, password, account);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(rcvProduit);
                } else {
                    Toast.makeText(getApplicationContext(), "hjhj " + listProduct.getMsg(), Toast.LENGTH_SHORT).show();
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
