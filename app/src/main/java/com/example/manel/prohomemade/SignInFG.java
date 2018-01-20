package com.example.manel.prohomemade;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.manel.prohomemade.DAO.CreateArtisant;
import com.example.manel.prohomemade.DAO.CreateClient;
import com.example.manel.prohomemade.model.ListPays;
import com.example.manel.prohomemade.model.Pays;
import com.example.manel.prohomemade.model.ToastDialog;
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

public class SignInFG extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    EditText txtTel, txtPassword, txtmatfisc;
    CreateClient createClient = new CreateClient();
    CreateArtisant createArtisant = new CreateArtisant();
    String designp;
    Spinner spinner;
    ArrayAdapter<String> adapter;
    ArrayList<String> listItems = new ArrayList<>();
    ArrayList<String> list = new ArrayList<>();
    Switch aSwitch;
    TextInputLayout txTextInputLayout;
    ToastDialog toastDialog;
    String name, prename, email;
    private String ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_fg);

        toastDialog = new ToastDialog(getApplicationContext());
        txTextInputLayout = (TextInputLayout) findViewById(R.id.txtinputlayoutmatfiscFG);
        txtTel = (EditText) findViewById(R.id.txttelSigninFG);
        txtPassword = (EditText) findViewById(R.id.txtpasswordSigninFG);
        txtmatfisc = (EditText) findViewById(R.id.txtmatfisclSigninFG);
        spinner = (Spinner) findViewById(R.id.spinnerpaysFG);
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.txtSpinnerD, listItems);
        LoadDataPays();
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        ca = "client";

        aSwitch = (Switch) findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ca = "client";
                    txTextInputLayout.setVisibility(View.INVISIBLE);
                } else {
                    ca = "artisant";
                    txTextInputLayout.setVisibility(View.VISIBLE);
                }
            }
        });


        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            name = (String) b.get("nom");
            prename = (String) b.get("prenom");
            //int tel = b.getInt("tel");
            email = (String) b.get("email");
            //String password = (String) b.get("password");
            Log.d("nameclient", name);
            Log.d("prenomclient", prename);
            Log.d("emailclient", email);
            //txtName.setText(name.toString());
            //txtEmail.setText(email);
            //account = (String) b.get("account");
            // btnVisivility(account);
            if (b.get("imgUrl") != null) {
                String imgUrl = (String) b.get("imgUrl");
                // Glide.with(this).load(imgUrl).into(profilePic);
            } else {
                // Glide.with(this).load(R.drawable.homemade101).into(profilePic);
            }
        }
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

    public void SignInV(View view) {
        String stel = txtTel.getText().toString();
        String pasw = txtPassword.getText().toString();
        int tel = 0;
        if (stel.matches("")) {
            toastDialog.ShowToast("Remplir tout les champs");
        } else {
            tel = Integer.parseInt(stel);

            if (pasw.matches("")) {
                toastDialog.ShowToast("Remplir tout les champs");
            } else {
                if (ca.matches("client")) {
                    createClient = new CreateClient();
                    if (createClient.createClient(name, prename, email, pasw, tel, designp)) {
                        ShowDialogSuccesC("Client", "Bienvenue! " + name + " " + prename + " ^^",
                                name, prename, tel, email, pasw);
                    } else {
                        toastDialog.ShowDialog("client", "nooot registre");
                    }
                } else if (ca.matches("artisant")) {
                    // artisant
                    String matfisc = txtmatfisc.getText().toString();
                    if (matfisc.matches("")) {
                        toastDialog.ShowDialog("Controle de saisie", "remplir tout les champs");
                    } else {
                        createArtisant = new CreateArtisant();
                        if (createArtisant.createArtisant(name, prename, email, tel, pasw, designp, txtmatfisc.getText().toString())) {
                            ShowDialogSuccesA("Artisant", "Bienvenue! " + name + " " + prename + " ^^",
                                    name, prename, tel, email, pasw, matfisc, designp);
                        } else {
                            toastDialog.ShowDialog("Artisant", "nooo");
                        }
                    }
                } else {
                    toastDialog.ShowToast("erreur switch");
                }
            }

        }

    }

    private void ShowDialogSuccesC(String titre, String msg,
                                   final String nom, final String prenom, final int tel, final String email, final String password) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(titre);
        alertDialog.setMessage(msg);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), ConnectedClient.class);
                        intent.putExtra("nom", nom);
                        intent.putExtra("prenom", prenom);
                        intent.putExtra("tel", tel);
                        intent.putExtra("email", email);
                        intent.putExtra("password", password);
                        //intent.putExtra("account", "btn");
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void ShowDialogSuccesA(String titre, String msg,
                                   final String nom, final String prenom, final int tel, final String email,
                                   final String password, final String matfisc, final String adr) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(titre);
        alertDialog.setMessage(msg);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), ConnectedArtisant.class);
                        intent.putExtra("nom", nom);
                        intent.putExtra("prenom", prenom);
                        intent.putExtra("tel", tel);
                        intent.putExtra("email", email);
                        intent.putExtra("password", password);
                        intent.putExtra("matfisc", matfisc);
                        intent.putExtra("adr", adr);
                        //intent.putExtra("account", "btn");
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
