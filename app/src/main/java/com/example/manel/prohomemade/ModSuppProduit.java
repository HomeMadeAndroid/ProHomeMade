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
import android.widget.TextView;
import android.widget.Toast;

import com.example.manel.prohomemade.DAO.ModifierProduit;
import com.example.manel.prohomemade.DAO.SupprimerProduit;
import com.example.manel.prohomemade.model.ToastDialog;

public class ModSuppProduit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    // ******************* Modifier Supprimer Produit

    String nom, prenom, email, password, account, matfisc;
    int tel;
    String cat;
    String idprod, nomp, catg, desc, prix, dispo;
    EditText txtNomp, txtdescrip, txtprixp;
    Spinner spinner;
    CheckBox chbdispo;
    ToastDialog toastDialog;
    ModifierProduit modifierProduit;
    SupprimerProduit supprimerProduit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_supp_produit);
        toastDialog = new ToastDialog(getApplicationContext());

        spinner = (Spinner) findViewById(R.id.spinnercatgp);
        txtNomp = (EditText) findViewById(R.id.txtnomp);
        txtdescrip = (EditText) findViewById(R.id.txtdescrip);
        txtprixp = (EditText) findViewById(R.id.txtprixp);
        chbdispo = (CheckBox) findViewById(R.id.checkboxDp);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            //********prod***********
            idprod = (String) b.get("idProd");
            nomp = (String) b.get("nomp");
            catg = (String) b.get("catgp");
            desc = (String) b.get("descp");
            prix = (String) b.get("prixp");
            dispo = (String) b.get("dispop");
            Log.d("idprod modsupp", idprod);
            Log.d("nomp modsupp", nomp);
            Log.d("catgp modsupp", catg);
            Log.d("descp modsupp", desc);
            Log.d("prixp modsupp", prix);
            Log.d("dispop modsupp", dispo);
            txtNomp.setText(nomp);
            txtdescrip.setText(desc);
            txtprixp.setText(String.valueOf(prix), TextView.BufferType.EDITABLE);


            //******compt********
            nom = (String) b.get("nom");
            prenom = (String) b.get("prenom");
            tel = b.getInt("tel");
            email = (String) b.get("email");
            password = (String) b.get("password");
            matfisc = (String) b.get("matfisc");
            account = (String) b.get("account");
            Log.d("nameartisant modsupp", nom);
            Log.d("prenomartisant modsupp", prenom);
            Log.d("emailartisant modsupp", email);
            Log.d("telartisant modsupp", String.valueOf(tel));
            Log.d("pswartisant modsupp", password);
            Log.d("matfiscartisant modsupp", matfisc);
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

    public void ModifierProduit(View view) {
        String nomp = txtNomp.getText().toString();
        String descrip = txtdescrip.getText().toString();
        float prixp = 0;
        try {
            prixp = Float.parseFloat(txtprixp.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "remplir tout les champs " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        String disponible = "disponible";
        if (chbdispo.isChecked()) {
            disponible = "disponible";
        } else {
            disponible = "non disponible";
        }
        if (nomp.matches("") || descrip.matches("") || txtprixp.getText().toString().length() == 0) {
            toastDialog.ShowDialog("Controle de saisie", "remplir tout les champs");
            //Toast.makeText(getApplicationContext(), "remplir tout les champs", Toast.LENGTH_LONG).show();
        } else {
            try {
                int idp = Integer.parseInt(idprod);
                modifierProduit = new ModifierProduit();
                if (modifierProduit.modifierProduit(idp, nomp, descrip, prixp, cat, disponible)) {
                    ShowDialogSucces("Modification ", "d'un produit", nom, prenom, tel, matfisc,
                            email, password);
                    Toast.makeText(getApplicationContext(), "produit modifier", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "produit no modifier", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "erreuuur " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void SupprimerProduit(View view) {
        try {
            int idp = Integer.parseInt(idprod);
            supprimerProduit = new SupprimerProduit();
            if (supprimerProduit.deleteProduit(idp)) {
                ShowDialogSucces("Supprision ", "d'un produit", nom, prenom, tel, matfisc,
                        email, password);
                Toast.makeText(getApplicationContext(), "produit Supprimer", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "produit no supprimer", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "erreuuur " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        cat = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
