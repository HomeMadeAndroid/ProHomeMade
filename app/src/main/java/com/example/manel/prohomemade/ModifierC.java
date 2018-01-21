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

import com.example.manel.prohomemade.DAO.ModifierClient;
import com.example.manel.prohomemade.model.ToastDialog;

public class ModifierC extends AppCompatActivity {

    String name, prename, email, password, account;
    int tel;
    EditText txtnom, txttel, txtprenom;
    ToastDialog toastDialog;
    ModifierClient modifierClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_c);
        toastDialog = new ToastDialog(getApplicationContext());

        txtnom = (EditText) findViewById(R.id.txtnomc);
        txtprenom = (EditText) findViewById(R.id.txtprenomc);
        txttel = (EditText) findViewById(R.id.txttelc);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            name = (String) b.get("nom");
            prename = (String) b.get("prenom");
            tel = b.getInt("tel");
            email = (String) b.get("email");
            password = (String) b.get("password");
            account = (String) b.get("account");
            Log.d("nameclient a modifier", name);
            Log.d("prenomclient a modifier", prename);
            Log.d("emailclient a modifier", email);
            Log.d("telclient a modifier", String.valueOf(tel));
            Log.d("pswclient a modifier", password);
            txtnom.setText(name);
            txtprenom.setText(prename);
            txttel.setText(String.valueOf(tel), TextView.BufferType.EDITABLE);
        }
    }

    public void ModifierClient(View view) {
        String nnom = txtnom.getText().toString();
        String nprenom = txtprenom.getText().toString();
        String ntel = txttel.getText().toString();
        int itel = 0;
        if (ntel.matches("")) {
            toastDialog.ShowDialog("champ missing", "saisie votre telephone");
        } else {
            itel = Integer.parseInt(ntel);
            if (nnom.matches("") || nprenom.matches("") || ntel.matches("")) {
                toastDialog.ShowDialog("Controle de saisie", "remplir tout les champs");
            } else {
                modifierClient = new ModifierClient();
                if (modifierClient.modifierClient(nnom, nprenom, itel, email, password)) {
                    ShowDialogSucces("Client", "Bienvenue! " + nnom + " " + nprenom + " ^^",
                            nnom, nprenom, itel, email, password);
                } else {
                    toastDialog.ShowDialog("client", "nooo");
                }
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
                        //intent.putExtra("account", "btn");
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
