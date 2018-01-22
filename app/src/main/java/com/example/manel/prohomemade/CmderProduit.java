package com.example.manel.prohomemade;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manel.prohomemade.DAO.CreateCmd;

public class CmderProduit extends AppCompatActivity {

    //********************  commander produit
    String name, prename;
    int tel;
    String idp;
    String email, password, account;
    EditText txtqte;
    CreateCmd createCmd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmder_produit);
        //Log.d("cmddddd cmd", "hhhhhhhh");
        txtqte = (EditText) findViewById(R.id.txtqte);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            Log.d("cmddddd b cmd", "hhhhhhhh");
            name = (String) b.get("nom");
            prename = (String) b.get("prenom");
            tel = b.getInt("tel");
            idp = (String) b.get("idProd");
            email = (String) b.get("email");
            password = (String) b.get("password");
            account = (String) b.get("account");
            Log.d("nameclient cmddd", name);
            Log.d("prmclient cmddd", prename);
            Log.d("emlclient cmddd", email);
            Log.d("telclient cmddd", String.valueOf(tel));
            Log.d("idProd cmddd", String.valueOf(idp));
            Log.d("pswclient cmddd", password);
            Log.d("pswclient cmddd", account);
        }
    }

    public void InsertCmd(View view) {
        String sqte = txtqte.getText().toString();
        int iqte = 0;
        if (sqte.length() == 0) {
            Toast.makeText(getApplicationContext(), "remplir tout les champs ", Toast.LENGTH_LONG).show();
        } else {
            try {
                iqte = Integer.parseInt(sqte);
                Log.d("ddddddd", "" + iqte);
                int idProd = Integer.parseInt(idp);
                createCmd = new CreateCmd();
                if (createCmd.createCmd(email, password, idProd, iqte)) {
                    ShowDialogSucces("Insertion ", "d'un nouveau commande", name, prename, tel,
                            email, password);
                    Toast.makeText(getApplicationContext(), "commande ajouter", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "commande no ajouter", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "erreuuur " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void ShowDialogSucces(String titre, String msg,
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
                        intent.putExtra("account", account);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
