package com.example.cg_cafe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.cg_cafe.R;
import com.example.cg_cafe.model.DataPemesanan;
import com.example.cg_cafe.page.order.detail.DetailOrderActivity;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterDataPemesanan extends RecyclerView.Adapter<AdapterDataPemesanan.HolderData>{
    private Context ctx;
    private List<DataPemesanan> listPemesanan;


    public AdapterDataPemesanan(Context ctx, List<DataPemesanan> listPemesanan) {
        this.ctx = ctx;
        this.listPemesanan = listPemesanan;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_pemesanan, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataPemesanan dp = listPemesanan.get(position);

        NumberFormat formatter = new DecimalFormat("#,###");
        double harga = Double.parseDouble(dp.getHarga());
        String sub_harga = formatter.format(harga);


        Glide.with(ctx).load("https://img.okezone.com/"+ dp.getGambarMenu()).into(holder.imgMenu);
        holder.tvId.setText(dp.getId());
        holder.tvNama.setText(dp.getNamaMenu());
        holder.tvHarga.setText("Rp. " + sub_harga + ",-");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("@imgMenu", "https://img.okezone.com/" + dp.getGambarMenu());
                bundle.putString("@id", dp.getId());
                bundle.putString("@name", dp.getNamaMenu());
                bundle.putString("@price", "Rp. " + sub_harga + ",-");
                intent.putExtras(bundle);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPemesanan.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvHarga;
        CardView cardView;
        ImageView imgMenu;

        public HolderData(@NonNull View itemView) {

            super(itemView);

            imgMenu = itemView.findViewById(R.id.imgMenu);
            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_namaMenu);
            tvHarga = itemView.findViewById(R.id.tv_harga);

            cardView = itemView.findViewById(R.id.DetailPemesanan);

        }
    }
}
