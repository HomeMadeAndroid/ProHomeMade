package com.example.manel.prohomemade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SignIn extends AppCompatActivity {

    EditText txtNom, txtPrenom, txtTel, txtEmail, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        txtNom = (EditText) findViewById(R.id.txtnomSignIn);
        txtPrenom = (EditText) findViewById(R.id.txtprenomSignIn);
        txtTel = (EditText) findViewById(R.id.txttelSignin);
        txtEmail = (EditText) findViewById(R.id.txtemailSignIn);
        txtPassword = (EditText) findViewById(R.id.txtpasswordSignin);
    }

    public void SignIn(View view) {

    }
}
