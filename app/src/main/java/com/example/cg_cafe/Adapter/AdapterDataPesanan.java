package com.example.cg_cafe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cg_cafe.R;
import com.example.cg_cafe.model.DataKasir;
import com.example.cg_cafe.page.process.detail.DetailPesananActivity;

import java.util.List;

public class AdapterDataPesanan extends RecyclerView.Adapter<AdapterDataPesanan.HolderData>{
    private Context ctx;
    private List<DataKasir> listPesanan;


    public AdapterDataPesanan(Context ctx, List<DataKasir> listPesanan) {
        this.ctx = ctx;
        this.listPesanan = listPesanan;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_proses, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataKasir dm = listPesanan.get(position);

        holder.tvId.setText(String.valueOf(dm.getId()));
//        holder.tvIdPesanan.set
        holder.tvMejaNo.setText(dm.getMejano());
        holder.tvStatus.setText(dm.getStatus());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailPesananActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("@id", String.valueOf(dm.getId()));
                bundle.putString("@nomor", dm.getMejano());
                bundle.putString("@status", dm.getStatus());
                bundle.putString("@namaMenu", dm.getNamaMenu());
                bundle.putString("@jumlahPesanan", dm.getJumlahPesan());
                bundle.putString("@catatan", dm.getCatatan());
                intent.putExtras(bundle);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPesanan.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvId,  tvMejaNo, tvStatus;
        CardView cardView;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvMejaNo = itemView.findViewById(R.id.tv_nomeja);
            tvStatus = itemView.findViewById(R.id.tv_status);

            cardView = itemView.findViewById(R.id.DetailPesanan);

        }
    }
}
