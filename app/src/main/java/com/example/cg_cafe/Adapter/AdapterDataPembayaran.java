package com.example.cg_cafe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cg_cafe.R;
import com.example.cg_cafe.model.DataKasir;
import com.example.cg_cafe.page.kasir.detail.DetailPembayaranActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterDataPembayaran extends RecyclerView.Adapter<AdapterDataPembayaran.HolderData>{
    private Context ctx;
    private List<DataKasir> listPembayaran;

    public AdapterDataPembayaran(Context ctx, List<DataKasir> listPembayaran) {
        this.ctx = ctx;
        this.listPembayaran = listPembayaran;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_kasir, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataKasir dm = listPembayaran.get(position);

        NumberFormat formatter = new DecimalFormat("#,###");
        double harga = Double.parseDouble(dm.getTotalharga());
        String sub_harga = formatter.format(harga);

        double hargaMenu = Double.parseDouble(dm.getHarga());
        String hargaM = formatter.format(hargaMenu);


        holder.tvId.setText(String.valueOf(dm.getId()));
        holder.tvMejaNo.setText(dm.getMejano());
        holder.tvStatus.setText(dm.getStatus());
        holder.tvTotalHarga.setText("Rp. " + sub_harga + ",-");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailPembayaranActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("@id", String.valueOf(dm.getId()));
                bundle.putString("@nomor", dm.getMejano());
                bundle.putString("@total", "Rp. " + sub_harga + ",-");
                bundle.putString("@status", dm.getStatus());
                bundle.putString("@namaMenu", dm.getNamaMenu());
                bundle.putString("@jumlahPesanan", dm.getJumlahPesan());
                bundle.putString("@harga", "Rp. " + hargaM + ",-");
                intent.putExtras(bundle);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPembayaran.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvId, tvMejaNo, tvStatus, tvTotalHarga;
        CardView cardView;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvMejaNo = itemView.findViewById(R.id.tv_nomeja);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvTotalHarga = itemView.findViewById(R.id.tv_totalharga);

            cardView = itemView.findViewById(R.id.DetailPembayaran);

        }
    }
}
