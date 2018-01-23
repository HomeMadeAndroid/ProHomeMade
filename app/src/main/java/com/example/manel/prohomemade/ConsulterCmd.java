package com.example.manel.prohomemade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.manel.prohomemade.Adapter.RecyclerViewAdapter;
import com.example.manel.prohomemade.model.ListeCmd;
import com.example.manel.prohomemade.service.APIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsulterCmd extends AppCompatActivity {
    String email, password, name, prename;
    int tel;
    String matfisc, account;
    RecyclerView recyclerView;
    private RecyclerViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulter_cmd);
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
        recyclerView = (RecyclerView) findViewById(R.id.rcvCmd);
        LoadDataPays();
    }

    public void LoadDataPays() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.dbURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<ListeCmd> call = api.ConsultCmd(email, password);
        call.enqueue(new Callback<ListeCmd>() {
            @Override
            public void onResponse(Call<ListeCmd> call, Response<ListeCmd> response) {
                ListeCmd listeCmd = new ListeCmd();
                listeCmd = response.body();
                String resk = response.body().toString();
                Log.d("resssssss ccmd", resk);
                Log.d("resssssss ccmd", email);
                Log.d("resssssss ccmd", password);
                if (listeCmd.getStatus() == 1) {
                    viewAdapter = new RecyclerViewAdapter(getApplicationContext(), listeCmd.getListCmd());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(viewAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), "hjhj " + listeCmd.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListeCmd> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("erreur: ", t.getMessage());
            }
        });
    }
}