package com.example.cg_cafe.page.order.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.cg_cafe.R;
import com.example.cg_cafe.page.home.HomeActivity;
import com.example.cg_cafe.utils.BaseActivity;

public class CheckoutActivity extends BaseActivity {
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

        headerName.setText("Checkout Pesanan");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckoutActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
