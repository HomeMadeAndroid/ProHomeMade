package com.example.manel.prohomemade;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class ParametreA extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private String account = "";
    private LinearLayout googleSection;
    private LinearLayout fcbSection;
    private LinearLayout btnSection;
    private Button btngSignout, btnfSignout, btnSignout;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre2);

        googleSection = (LinearLayout) findViewById(R.id.googleSection);
        googleSection.setVisibility(View.GONE);
        fcbSection = (LinearLayout) findViewById(R.id.facebookSection);
        fcbSection.setVisibility(View.GONE);
        btnSection = (LinearLayout) findViewById(R.id.btnSection);
        btnSection.setVisibility(View.GONE);

        btngSignout = (Button) findViewById(R.id.btngLogout);
        btngSignout.setOnClickListener(this);
        btnfSignout = (Button) findViewById(R.id.btnfLogout);
        btnfSignout.setOnClickListener(this);
        btnSignout = (Button) findViewById(R.id.btnLogout);
        btnSignout.setOnClickListener(this);

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();


        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            //String name = (String) b.get("name");
            //String email = (String) b.get("email");
            account = (String) b.get("account");
            btnVisivility(account);
            /*if (b.get("imgUrl") != null) {
                String imgUrl = (String) b.get("imgUrl");
                Glide.with(this).load(imgUrl).into(profilePic);
            } else {
                Glide.with(this).load(R.drawable.profilpic).into(profilePic);
            }
            txtName.setText(name);
            txtName.setText(name);
            Toast.makeText(getApplicationContext(), email, Toast.LENGTH_LONG);
            txtEmail.setText(email);*/
        }
    }

    public void btnVisivility(String account) {
        if (account.equalsIgnoreCase("Google")) {
            googleSection.setVisibility(View.VISIBLE);
            fcbSection.setVisibility(View.INVISIBLE);
            btnSection.setVisibility(View.INVISIBLE);
        } else if (account.equalsIgnoreCase("Facebook")) {
            fcbSection.setVisibility(View.VISIBLE);
            googleSection.setVisibility(View.INVISIBLE);
            btnSection.setVisibility(View.INVISIBLE);
        } else {
            btnSection.setVisibility(View.VISIBLE);
            googleSection.setVisibility(View.INVISIBLE);
            fcbSection.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btngLogout:
                signOutGoogle();
                break;
            case R.id.btnfLogout:
                signOutFcb();
                break;
            case R.id.btnLogout:
                signOut();
                break;
        }
    }

    private void signOut() {
        LoginManager.getInstance().logOut();
        Intent login = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(login);
        finish();
    }

    private void signOutFcb() {
        Intent login = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(login);
    }

    private void signOutGoogle() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                Toast.makeText(getApplicationContext(), "Déconnection", Toast.LENGTH_SHORT);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT);
    }
}