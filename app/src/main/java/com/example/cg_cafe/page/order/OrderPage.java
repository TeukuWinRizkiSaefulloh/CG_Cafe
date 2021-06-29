package com.example.cg_cafe.page.order;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cg_cafe.Adapter.AdapterDataPemesanan;
import com.example.cg_cafe.R;
import com.example.cg_cafe.api.ApiClient;
import com.example.cg_cafe.api.ApiInterface;
import com.example.cg_cafe.model.DataPemesanan;
import com.example.cg_cafe.model.PemesananModel;
import com.example.cg_cafe.page.order.checkout.CheckoutActivity;
import com.example.cg_cafe.utils.BaseFragment;
import com.example.cg_cafe.utils.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderPage extends BaseFragment {

    SessionManager sessionManager;
    private RecyclerView rvdata;
    private RecyclerView.Adapter adData;
    private List<DataPemesanan> listPemesanan = new ArrayList<>();
    FloatingActionButton btnChart;

    public OrderPage() {
        // Required empty public constructor
    }

    public static OrderPage newInstance(String param1, String param2) {
        OrderPage fragment = new OrderPage();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        btnChart = view.findViewById(R.id.btnChart);
        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CheckoutActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void init(View view) {
        sessionManager = new SessionManager(getActivity());
        rvdata = view.findViewById(R.id.rv_data_order);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        rvdata.setLayoutManager(gridLayoutManager);
        sessionManager.storeStringData("pesananPage", "pesananPage");
        retrieveData();
    }

    public void retrieveData(){
        final ApiInterface ardData = ApiClient.getClient().create(ApiInterface.class);
        Call<PemesananModel> tampilData = ardData.ardPemesananData();

        try {
            showDialog(true);
            tampilData.enqueue(new Callback<PemesananModel>() {
                @Override
                public void onResponse(Call<PemesananModel> call, Response<PemesananModel> response) {
                    showDialog(false);
                    int kode = response.body().getSuccess();
                    String message = response.body().getMessage();

//                    Toast.makeText(getActivity(), "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();

                    listPemesanan = response.body().getData();
                    adData = new AdapterDataPemesanan(getActivity(), listPemesanan);
                    rvdata.setAdapter(adData);
                    adData.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<PemesananModel> call, Throwable t) {
                    showMessage("Gagal Menghubung ke server");
                    showDialog(false);
                }
            });
        }catch (Exception e){
            showDialog(false);
            showMessage("Sebuah Error Terjadi" + e.getLocalizedMessage());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_page, container, false);
    }

}