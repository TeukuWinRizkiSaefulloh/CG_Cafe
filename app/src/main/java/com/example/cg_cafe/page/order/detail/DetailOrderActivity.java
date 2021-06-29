package com.example.cg_cafe.page.order.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.cg_cafe.R;
import com.example.cg_cafe.utils.BaseActivity;

public class DetailOrderActivity extends BaseActivity {
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

        headerName.setText("Detail Pesanan");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailOrderActivity.super.onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String productName = bundle.getString("@name");
            String productPrice = bundle.getString("@price");
            String imgMenu = bundle.getString("@imgMenu");
            loadFragment(new DetailOrderPage(imgMenu, productName, productPrice), fl);
            Log.e(imgMenu, "Image");
        }

    }
}
