package com.example.cg_cafe.page.history.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cg_cafe.R;
import com.example.cg_cafe.utils.BaseFragment;

public class DetailHistoryPage extends BaseFragment {

    String id, total, namaMenu, harga, jumlahPesanan, catatan;
    TextView tvId, tvTotal, tvNamaMenu, tvJumlahPesanan, tvHarga, tvCatatan;

    public DetailHistoryPage(String id, String namaMenu, String jumlahPesanan, String harga, String catatan, String total) {
        this.id = id;
        this.namaMenu = namaMenu;
        this.jumlahPesanan = jumlahPesanan;
        this.harga = harga;
        this.catatan = catatan;
        this.total = total;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_history_fragment, null,false);
    }

    void init(View view){
        tvId = view.findViewById(R.id.tv_id);
        tvTotal = view.findViewById(R.id.tv_total);
        tvNamaMenu = view.findViewById(R.id.tv_namaMenu);
        tvHarga = view.findViewById(R.id.tv_harga);
        tvCatatan = view.findViewById(R.id.tv_catatan);
        tvJumlahPesanan = view.findViewById(R.id.tv_jumlahPesanan);


        tvId.setText(id);
        tvTotal.setText(total);
        tvJumlahPesanan.setText(jumlahPesanan);
        tvCatatan.setText(catatan);
        tvNamaMenu.setText(namaMenu);
        tvHarga.setText(harga);
    }
}
