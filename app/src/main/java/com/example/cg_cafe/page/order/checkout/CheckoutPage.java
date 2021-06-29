package com.example.cg_cafe.page.order.checkout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cg_cafe.R;
import com.example.cg_cafe.model.DataKasir;
import com.example.cg_cafe.utils.BaseFragment;
import com.example.cg_cafe.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;


public class CheckoutPage extends BaseFragment {

    SessionManager sessionManager;
    private RecyclerView rvdata;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    TextView tvTotal;
    Button btnPesan;
    private List<DataKasir> listorder = new ArrayList<>();

    public CheckoutPage() {
        // Required empty public constructor
    }

    public static CheckoutPage newInstance(String param1, String param2) {
        CheckoutPage fragment = new CheckoutPage();
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
        tvTotal = view.findViewById(R.id.tv_total);
        btnPesan = view.findViewById(R.id.btn_pesan);
    }

    private void init(View view){
        sessionManager = new SessionManager(getActivity());
        rvdata = view.findViewById(R.id.list_chart);
        lmData = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvdata.setLayoutManager(lmData);
        String test = sessionManager.getStringData("pesananPage");
        Log.e("Testing sp", test);
//        retrieveData();
    }

//    public void retrieveData(){
//        final ApiInterface ardData = ApiClient.getClient().create(ApiInterface.class);
//        Call<ResponseModel> tampilData = ardData.ardKasirData();
//
//        try {
//            showDialog(true);
//            tampilData.enqueue(new Callback<ResponseModel>() {
//                @Override
//                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                    showDialog(false);
//                    int kode = response.body().getKode();
//                    String pesan = response.body().getPesan();
//
////                    Toast.makeText(getActivity(), "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
//
//                    listorder = response.body().getData();
//                    adData = new AdapterDataPembayaran(getActivity(), listorder);
//                    rvdata.setAdapter(adData);
//                    adData.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onFailure(Call<ResponseModel> call, Throwable t) {
//                    showMessage("Gagal Menghubung ke server");
//                    showDialog(false);
//                }
//            });
//        }catch (Exception e){
//            showDialog(false);
//            showMessage("Sebuah Error Terjadi" + e.getLocalizedMessage());
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_order_fragment, null, false);
    }

}