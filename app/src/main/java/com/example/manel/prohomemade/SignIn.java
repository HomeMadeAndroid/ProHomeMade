package com.example.manel.prohomemade;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.manel.prohomemade.DAO.CreateClient;
import com.example.manel.prohomemade.model.ListPays;
import com.example.manel.prohomemade.model.Pays;
import com.example.manel.prohomemade.service.APIService;
import com.example.manel.prohomemade.service.GererClient;
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
    CreateClient createClient = new CreateClient();
    EditText txtNom, txtPrenom, txtTel, txtEmail, txtPassword;
    Spinner spinner;
    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();
    String designp;
    private GererClient gererClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        spinner = (Spinner) findViewById(R.id.spinnerpays);
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.txtSpinnerD, listItems);
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
                    for (Iterator it = listOfPays.getListP().iterator(); it.hasNext(); ) {
                        ppp = (Pays) it.next();
                        list.add(ppp.getDesign());
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
        designp = ls;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "Selecte un pays", Toast.LENGTH_LONG).show();
    }

    public void SignIn(View view) {
        try {
            String nom = txtNom.getText().toString();
            String prenom = txtPrenom.getText().toString();
            String email = txtEmail.getText().toString();
            String stel = txtTel.getText().toString();
            int tel = 0;
            if (stel.matches("")) {
                ShowDialog("champ missing", "saisie votre telephone");
            } else {
                tel = Integer.parseInt(stel);
            }
            String password = txtPassword.getText().toString();

            if (nom.matches("") || prenom.matches("") || tel == 0 || password.matches("") || email.matches("")) {
                ShowDialog("Controle de saisie", "remplir tout les champs");
            } else {
                createClient = new CreateClient();
                if (createClient.createClient(nom, prenom, email, password, tel, designp)) {
                    ShowDialogSucces("Client", "Bienvenue! " + nom + " " + prenom + " ^^",
                            nom, prenom, password, tel);
                } else {
                    ShowDialog("client", "nooo");
                }
            }
        } catch (Exception e) {
            ShowDialog("wrong cast", e.getMessage());
        }
    }

    private void ShowDialogSucces(String titre, String msg,
                                  final String nom, final String email, final String password, final int tel) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(titre);
        alertDialog.setMessage(msg);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(SignIn.this, Connected.class);
                        intent.putExtra("email", email);
                        intent.putExtra("name", nom);
                        //intent.putExtra("account", "btn");
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void ShowDialog(String titre, String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(titre);
        alertDialog.setMessage(msg);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void ShowToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
    }

}
