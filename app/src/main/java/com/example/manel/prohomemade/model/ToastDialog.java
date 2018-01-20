package com.example.manel.prohomemade.model;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.manel.prohomemade.ConnectedArtisant;
import com.example.manel.prohomemade.ConnectedClient;

/**
 * Created by manel on 20/01/2018.
 */

public class ToastDialog {
    Context context;

    private void ShowDialogSucces(String titre, String msg,
                                  final String nom, final String email, final String password, final int tel) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(titre);
        alertDialog.setMessage(msg);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, ConnectedClient.class);
                        intent.putExtra("email", email);
                        intent.putExtra("name", nom);
                        //intent.putExtra("account", "btn");
                        context.startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void ShowDialog(String titre, String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
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
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    private void ShowDialogSuccesA(String titre, String msg,
                                   final String nom, final String prenom, final int tel, final String email,
                                   final String password, final String matfisc, final String adr) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(titre);
        alertDialog.setMessage(msg);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, ConnectedArtisant.class);
                        intent.putExtra("nom", nom);
                        intent.putExtra("prenom", prenom);
                        intent.putExtra("tel", tel);
                        intent.putExtra("email", email);
                        intent.putExtra("password", password);
                        intent.putExtra("matfisc", matfisc);
                        intent.putExtra("adr", adr);
                        //intent.putExtra("account", "btn");
                        context.startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void ShowDialogSuccesC(String titre, String msg,
                                   final String nom, final String prenom, final int tel, final String email, final String password) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(titre);
        alertDialog.setMessage(msg);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, ConnectedClient.class);
                        intent.putExtra("nom", nom);
                        intent.putExtra("prenom", prenom);
                        intent.putExtra("tel", tel);
                        intent.putExtra("email", email);
                        intent.putExtra("password", password);
                        //intent.putExtra("account", "btn");
                        context.startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
