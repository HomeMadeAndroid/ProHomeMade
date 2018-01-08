package com.example.manel.prohomemade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.manel.prohomemade.model.ListPays;
import com.example.manel.prohomemade.model.Pays;
import com.example.manel.prohomemade.service.APIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignIn extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    EditText txtNom, txtPrenom, txtTel, txtEmail, txtPassword;
    Spinner spinner;
    private String mSpinnerLabel = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        // spinner = (Spinner) findViewById(R.id.sp);
        // Create the spinner.
        /*spinner = (Spinner) findViewById(R.id.spinnerpays);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        // Create ArrayAdapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }*/


        txtNom = (EditText) findViewById(R.id.txtnomSignIn);
        txtPrenom = (EditText) findViewById(R.id.txtprenomSignIn);
        txtTel = (EditText) findViewById(R.id.txttelSignin);
        txtEmail = (EditText) findViewById(R.id.txtemailSignIn);
        txtPassword = (EditText) findViewById(R.id.txtpasswordSignin);

    }


    public void LoadDataPays() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.dbURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<ListPays> call = api.getAllPays();
        call.enqueue(new Callback<ListPays>() {
            @Override
            public void onResponse(Call<ListPays> call, Response<ListPays> response) {
                ListPays listOfPays = response.body();
                if (listOfPays.getStatus() == 1) {
                    Pays ppp = new Pays(0, " ");
                    Pays p = ppp;
                    for (Iterator it = listOfPays.getListP().iterator(); it.hasNext(); ) {
                        ppp = (Pays) it.next();
                        p.setId(ppp.getId());
                        p.setDesign(ppp.getDesign());
                        Log.e("id : ", "" + p.getId());
                        Log.e("design: ", "" + p.getDesign());
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "hjhj " + listOfPays.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListPays> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("erreur: ", t.getMessage());
            }
        });
    }


    public void SignIn(View view) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
