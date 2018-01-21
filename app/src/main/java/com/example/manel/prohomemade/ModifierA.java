package com.example.manel.prohomemade;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.manel.prohomemade.DAO.ModifierArtisant;
import com.example.manel.prohomemade.model.ToastDialog;

public class ModifierA extends AppCompatActivity {

    String name, prename, email, password, matfisc, account;
    int tel;
    EditText txtnom, txttel, txtprenom, txtmatfisc;
    ToastDialog toastDialog;
    ModifierArtisant modifierArtisant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);
        toastDialog = new ToastDialog(getApplicationContext());
        txtnom = (EditText) findViewById(R.id.txtnoma);
        txtprenom = (EditText) findViewById(R.id.txtprenoma);
        txttel = (EditText) findViewById(R.id.txttela);
        txtmatfisc = (EditText) findViewById(R.id.txtmatfisca);

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
            txtnom.setText(name);
            txtprenom.setText(prename);
            txtmatfisc.setText(matfisc);
            txttel.setText(String.valueOf(tel), TextView.BufferType.EDITABLE);
        }

    }

    public void ModifierArtisant(View view) {
        String nnom = txtnom.getText().toString();
        String nprenom = txtprenom.getText().toString();
        String ntel = txttel.getText().toString();
        String nmatfisc = txtmatfisc.getText().toString();
        int itel = 0;
        if (ntel.matches("")) {
            toastDialog.ShowDialog("champ missing", "saisie votre telephone");
        } else {
            itel = Integer.parseInt(ntel);
            if (nnom.matches("") || nprenom.matches("") || ntel.matches("") || nmatfisc.matches("")) {
                toastDialog.ShowDialog("Controle de saisie", "remplir tout les champs");
            } else {
                modifierArtisant = new ModifierArtisant();
                if (modifierArtisant.modifierArtisant(nnom, nprenom, itel, nmatfisc, email, password)) {
                    ShowDialogSucces("Artisant", "Bienvenue! " + nnom + " " + nprenom + " ^^",
                            nnom, nprenom, itel, nmatfisc, email, password);
                } else {
                    toastDialog.ShowDialog("client", "nooo");
                }
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
                        //intent.putExtra("account", "btn");
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
