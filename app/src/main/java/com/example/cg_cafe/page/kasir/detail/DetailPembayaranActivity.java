package com.example.cg_cafe.page.kasir.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cg_cafe.R;
import com.example.cg_cafe.utils.BaseActivity;

public class DetailPembayaranActivity extends BaseActivity {

    FrameLayout fl;
    TextView headerName;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fragment);
        fl = findViewById(R.id.containerFragments);
        headerName = findViewById(R.id.headerFragmentName);
        backButton = findViewById(R.id.backButtonFragment);

        headerName.setText("Detail Pembayaran");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailPembayaranActivity.super.onBackPressed();
            }
        });


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String id = bundle.getString("@id");
            String noMeja = bundle.getString("@nomor");
            String totalPembayaran = bundle.getString("@total");
            String status = bundle.getString("@status");
            String namaMenu = bundle.getString("@namaMenu");
            String jumlahPesanan = bundle.getString("@jumlahPesanan");
            String harga = bundle.getString("@harga");
            loadFragment(new DetailPembayaranPage(id, noMeja,totalPembayaran, status, namaMenu, jumlahPesanan, harga),fl);
        }
    }


}