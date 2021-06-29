package com.example.cg_cafe.page.process.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.cg_cafe.R;
import com.example.cg_cafe.utils.BaseActivity;

public class DetailPesananActivity extends BaseActivity {

    FrameLayout fl;
    TextView headerName;
    ImageView backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.base_fragment);
        fl = findViewById(R.id.containerFragments);
        headerName = findViewById(R.id.headerFragmentName);
        backButton = findViewById(R.id.backButtonFragment);

        headerName.setText("Detail Proses Pesanan");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailPesananActivity.super.onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String id = bundle.getString("@id");
            String noMeja = bundle.getString("@nomor");
            String status = bundle.getString("@status");
            String namaMenu = bundle.getString("@namaMenu");
            String jumlahPesanan = bundle.getString("@jumlahPesanan");
            String catatan = bundle.getString("@catatan");
            loadFragment(new DetailPesananPage(id, noMeja, status, namaMenu, jumlahPesanan, catatan), fl);
        }

    }
}
