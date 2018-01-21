package com.example.manel.prohomemade;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.manel.prohomemade.Adapter.RcvProduit;
import com.example.manel.prohomemade.model.ListProduit;
import com.example.manel.prohomemade.service.APIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by manel on 21/01/2018.
 */

public class FirstPageConnectedClient extends Fragment {
    RecyclerView recyclerView;
    View myview;
    private RcvProduit rcvProduit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.displayproduit, container, false);
        return myview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.rcvProduit);
        LoadDataProduit();
    }

    public void LoadDataProduit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.dbURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<ListProduit> call = api.getAllProduit();
        call.enqueue(new Callback<ListProduit>() {
            @Override
            public void onResponse(Call<ListProduit> call, Response<ListProduit> response) {
                ListProduit listProduct = response.body();
                if (listProduct.getStatus() == 1) {
                    Log.d("erreur: ", listProduct.getListP().toString());
                    rcvProduit = new RcvProduit(getActivity(), listProduct.getListP());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(rcvProduit);
                } else {
                    Toast.makeText(getActivity(), "hjhj " + listProduct.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListProduit> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("erreur: ", t.getMessage());
            }
        });
    }
}
