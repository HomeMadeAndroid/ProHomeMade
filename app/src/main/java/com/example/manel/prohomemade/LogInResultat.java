package com.example.manel.prohomemade;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class LogInResultat extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private TextView txtName;
    private TextView txtEmail;
    private ImageView profilePic;
    private Button btngSignout, btnfSignout, btnSignout;
    private GoogleApiClient googleApiClient;
    private String account = "";
    private LinearLayout googleSection;
    private LinearLayout fcbSection;
    private LinearLayout btnSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_resultat);

        googleSection = (LinearLayout) findViewById(R.id.googleSection);
        googleSection.setVisibility(View.GONE);
        fcbSection = (LinearLayout) findViewById(R.id.facebookSection);
        fcbSection.setVisibility(View.GONE);
        btnSection = (LinearLayout) findViewById(R.id.btnSection);
        btnSection.setVisibility(View.GONE);


        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

        btngSignout = (Button) findViewById(R.id.btngLogout);
        btngSignout.setOnClickListener(this);
        btnfSignout = (Button) findViewById(R.id.btnfLogout);
        btnfSignout.setOnClickListener(this);
        btnSignout = (Button) findViewById(R.id.btnLogout);
        btnSignout.setOnClickListener(this);

        txtName = (TextView) findViewById(R.id.txtName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        profilePic = (ImageView) findViewById(R.id.profilePic);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            String name = (String) b.get("name");
            String email = (String) b.get("email");
            account = (String) b.get("account");
            Log.d("account : ", account);
            btnVisivility(account);
            if (b.get("imgUrl") != null) {
                String imgUrl = (String) b.get("imgUrl");
                Glide.with(this).load(imgUrl).into(profilePic);
            } else {
                Glide.with(this).load(R.drawable.profilpic).into(profilePic);
            }
            txtName.setText(name);
            txtName.setText(name);
            Toast.makeText(getApplicationContext(), email, Toast.LENGTH_LONG);
            txtEmail.setText(email);
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

    public void btnVisivility(String account) {
        if (account.equalsIgnoreCase("Google")) {
            googleSection.setVisibility(View.VISIBLE);
            fcbSection.setVisibility(View.INVISIBLE);
            btnSection.setVisibility(View.INVISIBLE);
        } else if (account.equalsIgnoreCase("Facebook")) {
            fcbSection.setVisibility(View.VISIBLE);
            googleSection.setVisibility(View.INVISIBLE);
            btnSection.setVisibility(View.INVISIBLE);
        } else if (account.equalsIgnoreCase("btn")) {
            btnSection.setVisibility(View.VISIBLE);
            googleSection.setVisibility(View.INVISIBLE);
            fcbSection.setVisibility(View.INVISIBLE);
        }
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
