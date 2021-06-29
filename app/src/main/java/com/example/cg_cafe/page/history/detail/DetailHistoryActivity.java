package com.example.cg_cafe.page.history.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cg_cafe.R;
import com.example.cg_cafe.page.kasir.detail.DetailPembayaranPage;
import com.example.cg_cafe.utils.BaseActivity;
import com.example.cg_cafe.utils.SessionManager;

public class DetailHistoryActivity extends BaseActivity {

    SessionManager sessionManager;
    TextView headerName;
    ImageView onBack;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fragment);

        headerName = findViewById(R.id.headerFragmentName);
        onBack = findViewById(R.id.backButtonFragment);
        frameLayout = findViewById(R.id.containerFragments);

        headerName.setText("Detail History");
        onBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailHistoryActivity.super.onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String id = bundle.getString("@id");
            String total = bundle.getString("@total");
            String jumlahPesanan = bundle.getString("@jumlahPesanan");
            String harga = bundle.getString("@harga");
            String namaMenu = bundle.getString("@namaMenu");
            String catatan = bundle.getString("@catatan");
            loadFragment(new DetailHistoryPage(id, namaMenu, jumlahPesanan, harga, catatan, total),frameLayout);
        }
    }
}