package com.example.manel.prohomemade;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class Logactivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private static final int REC_CODE = 9001;
    LoginButton loginButton;
    TextView txt;
    CallbackManager callbackManager;
    private GoogleApiClient googleApiClient;
    private SignInButton btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_logactivity);

        btnSignin = (SignInButton) findViewById(R.id.btngLogin);
        btnSignin.setOnClickListener(this);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();


        loginButton = (LoginButton) findViewById(R.id.btnflogin);
        //txt = (TextView)findViewById(R.id.txtloginstatus);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();


                Intent intent = new Intent(Logactivity.this, LogInResultat.class);
                intent.putExtra("name", profile.getName());
                intent.putExtra("imgUrl", profile.getProfilePictureUri(100, 100).toString());
                //intent.putExtra("email", profile.getLastName());

                startActivity(intent);
                // get login name, email ...
            }

            @Override
            public void onCancel() {
                txt.setText("Login canceled");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btngLogin:
                signIn();
                break;
            /*case R.id.btnLogout:
                //signOut();
                break;*/
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REC_CODE);
    }

    /*private void signOut(){
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                //updateUI(false);
            }
        });
    }*/

    private void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();
            String imgUrl = null;
            if(account.getPhotoUrl()!=null){
                imgUrl = account.getPhotoUrl().toString();
            }
            Intent intent = new Intent(Logactivity.this, LogInResultat.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("imgUrl", imgUrl);
            startActivity(intent);
            //txtName.setText(name);
            //txtEmail.setText(email);
            //Glide.with(this).load(imgUrl).into(profilePic);
            //updateUI(true);
        } else {
            //updateUI(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REC_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }

    public void Inscri(View view) {
        Intent intent = new Intent(Logactivity.this, SignIn.class);
        //intent.putExtra("email", name);
        // intent.putExtra("password", email);
        startActivity(intent);
    }
}
