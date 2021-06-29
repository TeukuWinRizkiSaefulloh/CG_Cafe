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

import com.example.cg_cafe.model.DataHistory;
import com.example.cg_cafe.page.history.detail.DetailHistoryActivity;
import com.example.cg_cafe.R;
import com.example.cg_cafe.utils.FragmentInterfaces;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterDataHistory extends RecyclerView.Adapter<AdapterDataHistory.HolderData>{
    private Context ctx;
    private List<DataHistory> listHistory;
    FragmentInterfaces fi;

    public AdapterDataHistory(Context ctx, List<DataHistory> listHistory) {
        this.ctx = ctx;
        this.listHistory = listHistory;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_history, parent, false);
        HolderData holder = new HolderData(layout, fi);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataHistory dm = listHistory.get(position);

        NumberFormat formatter = new DecimalFormat("#,###");
        double harga = Double.parseDouble(dm.getSub_total());
        String sub_harga = formatter.format(harga);

        double hargaMenu = Double.parseDouble(dm.getHarga());
        String hargaM = formatter.format(hargaMenu);

        int totalPendapatan = 0;

        holder.tvIdPengguna.setText(dm.getIdPengguna());
        holder.tvId.setText(dm.getId());
        holder.tvStatus.setText(dm.getStatus());
        holder.tvTotalHarga.setText("Total Harga = Rp." + sub_harga + ",-");

        for(int i = 0; i < listHistory.size(); i ++){
            totalPendapatan = totalPendapatan + Integer.parseInt(listHistory.get(i).getSub_total());
//            fi.storeTotalPendapatan(totalPendapatan);
        }

//        State.setTotalPendapatan(totalPendapatan);
//        Preferences pref = new Preferences();
//        pref.setTotalPendapatan(totalPendapatan);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailHistoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("@id", String.valueOf(dm.getId()));
                bundle.putString("@status", dm.getStatus());
                bundle.putString("@namaMenu", dm.getNamaMenu());
                bundle.putString("@jumlahPesanan", dm.getJumlahPesan());
                bundle.putString("@catatan", dm.getCatatan());
                bundle.putString("@harga", "Rp. " + hargaM + ",-");
                bundle.putString("@total", "Rp. " + sub_harga + ",-");
                intent.putExtras(bundle);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listHistory.size() > 0){
            return listHistory.size();
        }else{
            return 1;
        }
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvIdPengguna, tvId, tvStatus, tvTotalHarga;
        CardView cardView;

        public HolderData(@NonNull View itemView, FragmentInterfaces fi) {
            super(itemView);

            tvIdPengguna = itemView.findViewById(R.id.tv_id);
            tvId = itemView.findViewById(R.id.tv_idPesanan);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvTotalHarga = itemView.findViewById(R.id.tv_totalharga);

            cardView = itemView.findViewById(R.id.DetailHistory);

        }
    }
}

final class State{
    static int totalPendapatan = 0;

    static int getTotalPendapatan(){
        return totalPendapatan;
    }

    static void setTotalPendapatan(int value){
        totalPendapatan = value;
    }
}
