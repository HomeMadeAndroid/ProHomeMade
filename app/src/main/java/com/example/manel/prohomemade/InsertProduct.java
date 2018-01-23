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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.manel.prohomemade.DAO.CreateArtisant;
import com.example.manel.prohomemade.DAO.CreateProduct;
import com.example.manel.prohomemade.model.ToastDialog;
import com.example.manel.prohomemade.service.GererProduit;

import java.util.ArrayList;

public class InsertProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ToastDialog toastDialog;
    String cat;
    CreateProduct createProduct = new CreateProduct();
    CreateArtisant createArtisant = new CreateArtisant();
    EditText txtNom, txtdescri, txtprix, txtnomart, txtemail;
    Spinner spinner;
    CheckBox dispo;
    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();
    String name, prename, matfisc;
    int tel;
    String email, password, account;
    private GererProduit gererproduit;
    private String mSpinnerLabel = "";
    private AdapterView adapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);
        spinner = (Spinner) findViewById(R.id.spinnercatgp);
        txtNom = (EditText) findViewById(R.id.txtnomp);
        txtdescri = (EditText) findViewById(R.id.txtdescrip);
        txtprix = (EditText) findViewById(R.id.txtprixp);
        //txtnomart = (EditText) findViewById(R.id.nomart);
        //txtemail = (EditText) findViewById(R.id.txtemail);
        dispo = (CheckBox) findViewById(R.id.checkboxDp);

        toastDialog = new ToastDialog(getApplicationContext());

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

        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        mSpinnerLabel = adapterView.getItemAtPosition(position).toString();
        cat = parent.getItemAtPosition(position).toString();
        //Toast.makeText(getApplicationContext(), "Selecte une categorie " + cat, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "Selecte une categorie ", Toast.LENGTH_LONG).show();
    }

    public void InsertProduit(View view) {

        String nomp = txtNom.getText().toString();
        String descrip = txtdescri.getText().toString();
        float prixp = 0;
        try {
            prixp = Float.parseFloat(txtprix.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "remplir tout les champs " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        String disponible = "disponible";
        if (dispo.isChecked()) {
            disponible = "disponible";
        } else {
            disponible = "non disponible";
        }
        if (nomp.matches("") || descrip.matches("") || txtprix.getText().toString().length() == 0) {
            toastDialog.ShowDialog("Controle de saisie", "remplir tout les champs");
            //Toast.makeText(getApplicationContext(), "remplir tout les champs", Toast.LENGTH_LONG).show();
        } else {
            try {
                createProduct = new CreateProduct();
                if (createProduct.createproduct(nomp, descrip, cat, prixp, disponible, name, email)) {
                    ShowDialogSucces("Insertion ", "d'un nouveau produit", name, prename, tel, matfisc,
                            email, password);
                        /*Toast.makeText(getApplicationContext(),
                                "nom : "+nom+" desc :"+descri+" prix: "+prix+" dispo: "+disponible
                                        +" nomart: "+nomart+" emailart: "+email,
                                Toast.LENGTH_LONG).show();*/
                    Toast.makeText(getApplicationContext(), "produit ajouter", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "produit no ajouter", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "erreuuur " + e.getMessage(), Toast.LENGTH_LONG).show();
            }


        }
    }

    private void ShowDialogSucces(String titre, String msg,
                                  final String nom, final String prenom, final int tel, final String matfisc, final String email, final String password) {
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
                        intent.putExtra("account", account);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}