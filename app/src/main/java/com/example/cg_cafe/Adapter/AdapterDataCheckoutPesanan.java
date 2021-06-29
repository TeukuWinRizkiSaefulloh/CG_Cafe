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
import com.example.cg_cafe.model.DataPemesanan;
import com.example.cg_cafe.model.Order;
import com.example.cg_cafe.page.order.detail.DetailOrderActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterDataCheckoutPesanan extends RecyclerView.Adapter<AdapterDataCheckoutPesanan.HolderData> {

    private Context ctx;
    private List<Order> listPemesanan;


    public AdapterDataCheckoutPesanan(Context ctx, List<Order> listPemesanan) {
        this.ctx = ctx;
        this.listPemesanan = listPemesanan;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_checkout, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        Order dp = listPemesanan.get(position);

        NumberFormat formatter = new DecimalFormat("#,###");
        double harga = Double.parseDouble(dp.getHarga());
        String sub_harga = formatter.format(harga);

        holder.tvId.setText(String.valueOf(dp.getIdMenu()));
        holder.tvNama.setText(dp.getNamaMenu());
        holder.tvJumlahPesanan.setText(dp.getJumlahPesanan());
        holder.tvHarga.setText(dp.getHarga());
        holder.tvTotal.setText("Rp. " + sub_harga + ",-");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("@id", String.valueOf(dp.getIdMenu()));
                bundle.putString("@name", dp.getNamaMenu());
                bundle.putString("@jumlahPesanan", dp.getJumlahPesanan());
                bundle.putString("@harga", dp.getHarga());
                bundle.putString("@price", "Rp. " + sub_harga + ",-");
                intent.putExtras(bundle);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvJumlahPesanan, tvHarga, tvTotal;
        CardView cardView;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_namaMenu);
            tvJumlahPesanan = itemView.findViewById(R.id.tv_jumlahPesanan);
            tvHarga = itemView.findViewById(R.id.tv_harga);
            tvTotal = itemView.findViewById(R.id.tv_total);

            cardView = itemView.findViewById(R.id.DetailPemesanan);

        }
    }
}
