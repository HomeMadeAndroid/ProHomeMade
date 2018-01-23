package com.example.manel.prohomemade.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manel.prohomemade.R;
import com.example.manel.prohomemade.model.Cmd;

import java.util.List;

/**
 * Created by manel on 23/01/2018.
 */

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Cmd> listPays;

    public RecyclerViewAdapter(Context context, List<Cmd> listCmd) {
        this.context = context;
        this.listPays = listCmd;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cmditem, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Cmd pays = listPays.get(position);
        holder.txtnomp.setText(String.valueOf(pays.getNomP()));
        holder.txtnom.setText(String.valueOf(pays.getNom()));
        holder.txttel.setText(String.valueOf(pays.getTel()));
    }

    @Override
    public int getItemCount() {
        if (listPays == null) {
            return 0;
        } else {
            return listPays.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView txtnom;
        public final TextView txtnomp;
        public final TextView txttel;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txtnom = (TextView) itemView.findViewById(R.id.txtnomClientcmd);
            this.txtnomp = (TextView) itemView.findViewById(R.id.txtnomPcmd);
            this.txttel = (TextView) itemView.findViewById(R.id.txttelClientcmd);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Intent intent = new Intent(context, Logactivity.class);
            //context.startActivity(intent);
        }
    }
}
