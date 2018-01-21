package com.example.manel.prohomemade.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manel.prohomemade.CmderProduit;
import com.example.manel.prohomemade.ModSuppProduit;
import com.example.manel.prohomemade.R;
import com.example.manel.prohomemade.model.Produit;

import java.util.List;

/**
 * Created by manel on 21/01/2018.
 */

public class RcvProduit extends RecyclerView.Adapter<RcvProduit.ViewHolder> {
    Context context;
    List<Produit> listP;
    String cnt;

    public RcvProduit(Context context, List<Produit> listP, String cnt) {
        this.context = context;
        this.listP = listP;
        this.cnt = cnt;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.produititem, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Produit produit = listP.get(position);
        holder.txtid.setText(String.valueOf(produit.getId()));
        Log.d("id produitt", "" + produit.getId());
        holder.txtidArt.setText(String.valueOf(produit.getIdArt()));
        Log.d("idArt produitt", "" + produit.getIdArt());
        holder.txtnom.setText(produit.getNom());
        Log.d("nom produitt", "" + produit.getNom());
        holder.txtdescri.setText(produit.getDescri());
        Log.d("descri produitt", "" + produit.getDescri());
        holder.txtcatg.setText(produit.getCatg());
        Log.d("catg produitt", "" + produit.getCatg());
        holder.txtprix.setText(String.valueOf(produit.getPrix()));
        Log.d("prix produitt", "" + produit.getPrix());
        holder.txtdateProd.setText(produit.getDateProd());
        Log.d("date produitt", "" + produit.getDateProd());
        holder.txtdispo.setText(produit.getDispo());
        Log.d("disp produitt", "" + produit.getDispo());
        holder.txtimg.setText(produit.getImg());
        Log.d("img produitt", "" + produit.getImg());
    }

    @Override
    public int getItemCount() {
        return listP.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView txtid;
        private final TextView txtidArt;
        private final TextView txtnom;
        private final TextView txtdescri;
        private final TextView txtcatg;
        private final TextView txtprix;
        private final TextView txtdateProd;
        private final TextView txtdispo;
        private final TextView txtimg;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txtid = (TextView) itemView.findViewById(R.id.txtIdProd);
            this.txtidArt = (TextView) itemView.findViewById(R.id.txtidArtP);
            this.txtnom = (TextView) itemView.findViewById(R.id.txtnomProd);
            this.txtdescri = (TextView) itemView.findViewById(R.id.txtDescP);
            this.txtcatg = (TextView) itemView.findViewById(R.id.txtCatgP);
            this.txtprix = (TextView) itemView.findViewById(R.id.txtprixP);
            this.txtdateProd = (TextView) itemView.findViewById(R.id.txtdateProd);
            this.txtdispo = (TextView) itemView.findViewById(R.id.txtdispoP);
            this.txtimg = (TextView) itemView.findViewById(R.id.txtimg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // details produit
            //Intent intent = new Intent(context, Logactivity.class);
            //context.startActivity(intent);
            if (cnt.matches("art")) {
                Intent intent = new Intent(context, ModSuppProduit.class);
                intent.putExtra("idProd", txtid.getText().toString());
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, CmderProduit.class);
                intent.putExtra("idProd", txtid.getText().toString());
                context.startActivity(intent);
            }
        }
    }
}
