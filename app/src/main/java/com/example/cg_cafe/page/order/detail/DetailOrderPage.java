package com.example.cg_cafe.page.order.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.cg_cafe.R;
import com.example.cg_cafe.utils.BaseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailOrderPage extends BaseFragment {
    String productName, productPrice, jumlahPesanan;
    String imgMenu;
    ImageView image;
    TextView txNama, txHarga, txKeterangan;
    ElegantNumberButton numberButton;
    FloatingActionButton btnChart;

    public DetailOrderPage(String imgMenu, String productName, String productPrice) {
        this.imgMenu = imgMenu;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_order_fragment, null, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

        Log.e(productName, "Product Ada");
        Log.e(productPrice, "Harga Product Ada");
        Log.e(imgMenu, "Image ada");
        numberButton = view.findViewById(R.id.BtnNumber);
        btnChart = view.findViewById(R.id.btnChart);
        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });



    }


    private void init(View view){
        txHarga = view.findViewById(R.id.txtHarga);
        txNama = view.findViewById(R.id.txNama);
        image = view.findViewById(R.id.imgMenu);

//        glide.load(glide, imgMenu, image);
        txHarga.setText(productPrice);
        txNama.setText(productName);
    }
}
