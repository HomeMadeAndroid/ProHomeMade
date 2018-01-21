package com.example.manel.prohomemade;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manel.prohomemade.model.Artisant;
import com.example.manel.prohomemade.model.Client;
import com.example.manel.prohomemade.service.APIService;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Logactivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener, GraphRequest.GraphJSONObjectCallback {

    private static final int REC_CODE = 9001;
    //private static final int fREC_CODE = 1001;
    LoginButton loginButton;
    CallbackManager callbackManager;
    AutoCompleteTextView txtEmail;
    EditText txtpsw;
    String em = "n";
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


        //******************* Facebook ******************
        loginButton = (LoginButton) findViewById(R.id.btnflogin);
        loginButton.setReadPermissions("email");
        callbackManager = new CallbackManager.Factory().create();
        //callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("Main", response.toString());
                                try {
                                    Profile profile = Profile.getCurrentProfile();
                                    //Intent intent = new Intent(Logactivity.this, ConnectedClient.class);
                                    //intent.putExtra("name", profile.getName());
                                    //intent.putExtra("email", response.getJSONObject().getString("email"));
                                    //intent.putExtra("imgUrl", profile.getProfilePictureUri(100, 100).toString());

                                    chekExistanceFG(response.getJSONObject().getString("email"),
                                            profile.getFirstName(), profile.getLastName(),
                                            profile.getProfilePictureUri(73, 73).toString(),
                                            "Facebook");

                                    //intent.putExtra("account", "Facebook");
                                    //startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,birthday,address");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Connect Canceled", Toast.LENGTH_SHORT);
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_SHORT);
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

    //***************************** Google
    private void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getGivenName();
            String prename = account.getFamilyName();
            String email = account.getEmail();
            String imgUrl = null;
            if (account.getPhotoUrl() != null) {
                imgUrl = account.getPhotoUrl().toString();
            }
            chekExistanceFG(email, name, prename, imgUrl, "Google");
            /*Intent intent = new Intent(Logactivity.this, ConnectedClient.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("imgUrl", imgUrl);
            intent.putExtra("account", "Google");
            startActivity(intent);*/
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
        /* else if(requestCode == fREC_CODE){
            callbackManager.onActivityResult(requestCode, resultCode, data);
        } else {
            Log.d("failed to connect", "no fcb no google");
        }*/
        }

    public void Inscri(View view) {
        Intent intent = new Intent(Logactivity.this, SignIn.class);
        startActivity(intent);
    }

    public void SignInbtn(View view) {
        final String email = txtEmail.getText().toString();
        final String psw = txtpsw.getText().toString();
        chekExistance(email, psw);
    }

    public void chekExistance(final String email, final String psw) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.dbURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        final APIService api = retrofit.create(APIService.class);
        Call<Client> call = api.CheckClient(email, psw);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                Client clt = response.body();
                Log.d("valide Request", clt.toString());
                if (response.isSuccessful()) {
                    Log.d("Clienttt found", "valide Request");
                    Log.d("Clienttt found", clt.getNom());
                    ShowDialogSuccesC("Bienvebue :)", clt.getNom() + " " + clt.getPrenom(),
                            clt.getNom(), clt.getPrenom(), clt.getTel(),
                            clt.getEmail(), clt.getPassword(), "btn");
                } else {
                    Log.d("Clienttt not found", "invalide Request");
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                //ShowDialogE(":( u r not regigstred"," do u want to sign in??");
                Log.d("search client failed", "invalide Request " + t.getMessage());
                Call<Artisant> call2 = api.CheckArtisant(email, psw);
                call2.enqueue(new Callback<Artisant>() {
                    @Override
                    public void onResponse(Call<Artisant> call, Response<Artisant> response) {
                        Artisant art = response.body();
                        Log.d("valide Request", art.toString());
                        if (response.isSuccessful()) {
                            Log.d("Artisantttt found", "valide Request");
                            Log.d("Artisant found", art.getNom());
                            ShowDialogSuccesA("Bienvebue :)", art.getNom() + " " + art.getPrenom(),
                                    art.getNom(), art.getPrenom(), art.getTel(), art.getEmail(), art.getPassword(),
                                    art.getMatfisc(), art.getAdr(), "btn");
                        } else {
                            Log.d("Artisanttt not found", "invalide Request");
                        }
                    }

                    @Override
                    public void onFailure(Call<Artisant> call, Throwable t) {
                        ShowDialogE(":( u r not regigstred", " do u want to sign in??");
                        Log.d("search Artisant failed", "invalide Request " + t.getMessage());

                    }
                });
            }
        });
    }

    @Override
    public void onCompleted(JSONObject object, GraphResponse response) {
        Log.e("jsdata", object.toString());
    }


    //**********************************

    public void ShowDialog(String titre, String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
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
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    private void ShowDialogSuccesA(String titre, String msg,
                                   final String nom, final String prenom, final int tel, final String email,
                                   final String password, final String matfisc, final String adr, final String account) {
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
                        intent.putExtra("adr", adr);
                        intent.putExtra("account", "account");
                        //intent.putExtra("account", "btn");
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void ShowDialogSuccesAFG(String titre, String msg,
                                     final String nom, final String prenom, final int tel,
                                     final String email, final String password,
                                     final String matfisc, final String adr,
                                     final String imgUrl, final String account) {
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
                        intent.putExtra("adr", adr);
                        intent.putExtra("imgUrl", imgUrl);
                        intent.putExtra("account", account);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void ShowDialogSuccesC(String titre, String msg,
                                   final String nom, final String prenom, final int tel, final String email,
                                   final String password, final String account) {
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

    private void ShowDialogSuccesCFG(String titre, String msg,
                                     final String nom, final String prenom, final int tel, final String email,
                                     final String password, final String imgUrl, final String account) {
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
                        intent.putExtra("imgUrl", imgUrl);
                        intent.putExtra("account", account);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void ShowDialogE(String titre, String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(titre);
        alertDialog.setMessage(msg);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), SignIn.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void ShowDialogEFG(String titre, String msg, final String email, final String nom, final String prenom) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(titre);
        alertDialog.setMessage(msg);
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), SignInFG.class);
                        intent.putExtra("nom", nom);
                        intent.putExtra("prenom", prenom);
                        intent.putExtra("email", email);
                        //intent.putExtra("imgUrl", imgUrl);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


    public void chekExistanceFG(final String email,
                                final String nom,
                                final String prenom,
                                final String imgUrl,
                                final String account) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.dbURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        final APIService api = retrofit.create(APIService.class);
        Call<Client> call = api.CheckClientFG(email);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                Client clt = response.body();
                Log.d("valide Request", clt.toString());
                if (response.isSuccessful()) {
                    Log.d("Clienttt found", "valide Request");
                    Log.d("Clienttt found", clt.getNom());
                    ShowDialogSuccesCFG("Bienvebue :)", clt.getNom() + " " + clt.getPrenom(),
                            clt.getNom(), clt.getPrenom(),
                            clt.getTel(), clt.getEmail(),
                            clt.getPassword(), imgUrl,
                            account);
                } else {
                    Log.d("Clienttt not found", "invalide Request");
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                //ShowDialogE(":( u r not regigstred"," do u want to sign in??");
                Log.d("search client failed", "invalide Request " + t.getMessage());
                Call<Artisant> call2 = api.CheckArtisantFG(email);
                call2.enqueue(new Callback<Artisant>() {
                    @Override
                    public void onResponse(Call<Artisant> call, Response<Artisant> response) {
                        Artisant art = response.body();
                        Log.d("valide Request", art.toString());
                        if (response.isSuccessful()) {
                            Log.d("Artisantttt found", "valide Request");
                            Log.d("Artisant found", art.getNom());
                            ShowDialogSuccesAFG("Bienvebue :)", art.getNom() + " " + art.getPrenom(),
                                    art.getNom(), art.getPrenom(), art.getTel(), art.getEmail(), art.getPassword(),
                                    art.getMatfisc(), art.getAdr(), imgUrl, account);
                        } else {
                            Log.d("Artisanttt not found", "invalide Request");
                        }
                    }

                    @Override
                    public void onFailure(Call<Artisant> call, Throwable t) {
                        ShowDialogEFG(":( u r not registred with us", " do u want to sign in??", email, nom, prenom);
                        Log.d("search Artisant failed", "invalide Request " + t.getMessage());

                    }
                });
            }
        });
    }
}
