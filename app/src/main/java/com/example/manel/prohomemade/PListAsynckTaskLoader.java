package com.example.manel.prohomemade;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.manel.prohomemade.model.ListPays;
import com.example.manel.prohomemade.model.Pays;
import com.example.manel.prohomemade.service.APIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by manel on 12/01/2018.
 */

public class PListAsynckTaskLoader extends AsyncTask<Void, Void, Void> {
    Context context;
    Spinner spinner;
    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<String> list;

    public PListAsynckTaskLoader(Context context, ArrayList<String> listItems, ArrayAdapter<String> adapter) {
        this.context = context;
        this.listItems = listItems;
        this.adapter = adapter;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        list = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String result = "";
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
                        list.add(ppp.getDesign());
                        Log.d("design: ", "" + p.getDesign());
                    }
                } else {
                    Toast.makeText(context, "hjhj " + listOfPays.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListPays> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("erreur: ", t.getMessage());
            }
        });
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        listItems.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
