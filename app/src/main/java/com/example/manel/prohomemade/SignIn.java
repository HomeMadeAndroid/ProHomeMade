package com.example.manel.prohomemade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.manel.prohomemade.model.ListPays;
import com.example.manel.prohomemade.model.Pays;
import com.example.manel.prohomemade.service.APIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignIn extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    EditText txtNom, txtPrenom, txtTel, txtEmail, txtPassword;
    Spinner spinner;
    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        spinner = (Spinner) findViewById(R.id.spinnerpays);
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.txtSpineer, listItems);
        LoadDataPays();
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        txtNom = (EditText) findViewById(R.id.txtnomSignIn);
        txtPrenom = (EditText) findViewById(R.id.txtprenomSignIn);
        txtTel = (EditText) findViewById(R.id.txttelSignin);
        txtEmail = (EditText) findViewById(R.id.txtemailSignIn);
        txtPassword = (EditText) findViewById(R.id.txtpasswordSignin);
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        BackTask bt=new BackTask();
        bt.execute();
    }*/

    public void LoadDataPays() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.dbURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<ListPays> call = api.getAllPays();
        call.enqueue(new Callback<ListPays>() {
            @Override
            public void onResponse(Call<ListPays> call, Response<ListPays> response) {
                ListPays listOfPays = response.body();
                if (listOfPays.getStatus() == 1) {
                    Pays ppp = new Pays(0, " ");
                    Pays p = ppp;
                    for (Iterator it = listOfPays.getListP().iterator(); it.hasNext(); ) {
                        ppp = (Pays) it.next();
                        list.add(ppp.getDesign());
                        Log.d("design: ", "" + p.getDesign());
                    }
                    listItems.addAll(list);
                    spinner.setAdapter(adapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to load data " + listOfPays.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListPays> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("erreur: ", t.getMessage());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String ls = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), ls, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "Selecte un pays", Toast.LENGTH_LONG).show();
    }


    public void SignIn(View view) {

    }

}
