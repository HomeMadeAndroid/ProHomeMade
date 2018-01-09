package com.example.manel.prohomemade;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

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
    CallbackManager callbackManager;
    AutoCompleteTextView txtEmail;
    EditText txtpsw;
    private GoogleApiClient googleApiClient;
    private SignInButton btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_logactivity);

        // callbackManager = new CallbackManager.Factory().create();

        txtEmail = (AutoCompleteTextView) findViewById(R.id.txtLogInemail);
        txtpsw = (EditText) findViewById(R.id.txtLogInpassword);

        btnSignin = (SignInButton) findViewById(R.id.btngLogin);
        btnSignin.setOnClickListener(this);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();


        loginButton = (LoginButton) findViewById(R.id.btnflogin);
        callbackManager = new CallbackManager.Factory().create();
        //callbackManager = CallbackManager.Factory.create();
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
                Toast.makeText(getApplicationContext(), "Connect Canceled", Toast.LENGTH_SHORT);
            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REC_CODE);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
            if (account.getPhotoUrl() != null) {
                imgUrl = account.getPhotoUrl().toString();
            }
            Intent intent = new Intent(Logactivity.this, LogInResultat.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("imgUrl", imgUrl);
            startActivity(intent);
        } else {
            Log.d("GoogleSignIn ", " Resultat");
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
        startActivity(intent);
    }

    public void Register(View view) {
        String imgUrl = null;
        Intent intent = new Intent(Logactivity.this, LogInResultat.class);
        intent.putExtra("email", txtEmail.getText().toString());
        intent.putExtra("name", txtpsw.getText().toString());
        intent.putExtra("imgUrl", imgUrl);
        startActivity(intent);
    }
}
