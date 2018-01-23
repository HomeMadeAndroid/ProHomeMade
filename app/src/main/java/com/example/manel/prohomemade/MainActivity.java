package com.example.manel.prohomemade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.manel.prohomemade.Adapter.RcvProduitMain;
import com.example.manel.prohomemade.model.ListProduit;
import com.example.manel.prohomemade.service.APIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private RcvProduitMain viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        LoadDataPays();
    }

    public  void LoadDataPays(){
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
                ListProduit listOfPays = response.body();
                if(listOfPays.getStatus()==1){
                    viewAdapter = new RcvProduitMain(getApplicationContext(), listOfPays.getListP());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(viewAdapter);
                }else{
                    Toast.makeText(MainActivity.this,"hjhj "+ listOfPays.getMsg(), Toast.LENGTH_SHORT).show();
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
