package com.example.manel.prohomemade;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class GLogin extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private LinearLayout profilSection;
    private Button btnSignout;
    private TextView txtName,txtEmail;
    private SignInButton btnSignin;
    private ImageView profilePic;
    private GoogleApiClient googleApiClient;
    private static final int REC_CODE = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glogin);
        profilSection = (LinearLayout)findViewById(R.id.profileSection);
        btnSignout = (Button)findViewById(R.id.btnLogout);
        btnSignin = (SignInButton)findViewById(R.id.btnLogin);
        txtName = (TextView)findViewById(R.id.txtName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        profilePic = (ImageView)findViewById(R.id.profilePic);
        btnSignin.setOnClickListener(this);
        btnSignout.setOnClickListener(this);
        profilSection.setVisibility(View.GONE);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnLogin:
                signIn();
                break;
            case R.id.btnLogout:
                signOut();
                break;
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REC_CODE);
    }

    private void signOut(){
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    private void handleResult(GoogleSignInResult result){
        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();
            String imgUrl = account.getPhotoUrl().toString();
            txtName.setText(name);
            txtEmail.setText(email);
            Glide.with(this).load(imgUrl).into(profilePic);
            updateUI(true);
        }
        else {
            updateUI(false);
        }
    }

    private void updateUI(Boolean isLogin){
        if(isLogin){
            profilSection.setVisibility(View.VISIBLE);
            btnSignin.setVisibility(View.GONE);
        }else{
            profilSection.setVisibility(View.GONE);
            btnSignin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REC_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }
}
