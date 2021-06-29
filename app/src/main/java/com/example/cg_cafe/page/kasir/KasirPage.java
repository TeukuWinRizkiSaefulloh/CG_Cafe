package com.example.cg_cafe.page.kasir;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cg_cafe.Adapter.AdapterDataPembayaran;
import com.example.cg_cafe.R;
import com.example.cg_cafe.api.ApiClient;
import com.example.cg_cafe.api.ApiInterface;
import com.example.cg_cafe.model.DataKasir;
import com.example.cg_cafe.model.ResponseModel;
import com.example.cg_cafe.utils.BaseFragment;
import com.example.cg_cafe.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KasirPage extends BaseFragment {

    SessionManager sessionManager;
    private RecyclerView rvdata;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataKasir> listPembayaran = new ArrayList<>();

    public KasirPage() {
        // Required empty public constructor
    }


    public static KasirPage newInstance(String param1, String param2) {
        KasirPage fragment = new KasirPage();
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
    }

    private void init(View view){
        sessionManager = new SessionManager(getActivity());
        rvdata = view.findViewById(R.id.rv_data_kasir);
        lmData = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvdata.setLayoutManager(lmData);
        String test = sessionManager.getStringData("pesananPage");
        Log.e("Testing sp", test);
        retrieveData();
    }

    public void retrieveData(){
        final ApiInterface ardData = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseModel> tampilData = ardData.ardKasirData();

        try {
            showDialog(true);
            tampilData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    showDialog(false);
                    int kode = response.body().getSuccess();
                    String message = response.body().getMessage();

//                    Toast.makeText(getActivity(), "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();

                    listPembayaran = response.body().getData();
                    adData = new AdapterDataPembayaran(getActivity(), listPembayaran);
                    rvdata.setAdapter(adData);
                    adData.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    showMessage("Gagal Menghubung ke server");
                    showDialog(false);
                }
            });
        }catch (Exception e){
            showDialog(false);
            showMessage("Sebuah Error Terjadi" + e.getLocalizedMessage());
        }
    }

    private void loginCheck(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kasir_page, container, false);
    }
}