package com.example.manel.prohomemade.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manel.prohomemade.Logactivity;
import com.example.manel.prohomemade.R;
import com.example.manel.prohomemade.model.Pays;

import java.util.List;

/**
 * Created by manel on 24/12/2017.
 */

public class RecyclerViewAdapter1 extends RecyclerView.Adapter<RecyclerViewAdapter1.ViewHolder> {

    private Context context;
    private List<Pays> listPays;

    public RecyclerViewAdapter1(Context context, List<Pays> listPays) {
        this.context = context;
        this.listPays = listPays;
    }

    @Override
    public RecyclerViewAdapter1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.payslistitem_main, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter1.ViewHolder holder, int position) {
        Pays pays = listPays.get(position);
        holder.txtId.setText(String.valueOf(pays.getId()));
        holder.txtDesign.setText(pays.getDesign());
    }

    @Override
    public int getItemCount() {
        return listPays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView txtId;
        public final TextView txtDesign;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txtDesign = (TextView) itemView.findViewById(R.id.txtDesign);
            this.txtId = (TextView) itemView.findViewById(R.id.txtId);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, Logactivity.class);
            context.startActivity(intent);
        }
    }
}
