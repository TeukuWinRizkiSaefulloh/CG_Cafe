package com.example.cg_cafe.page.process.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cg_cafe.R;
import com.example.cg_cafe.api.ApiClient;
import com.example.cg_cafe.api.ApiInterface;
import com.example.cg_cafe.model.ResponseModel;
import com.example.cg_cafe.page.home.HomeActivity;
import com.example.cg_cafe.utils.BaseFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPesananPage extends BaseFragment {

    String id, noMeja, status, namaMenu, jumlahPesanan, catatan;
    TextView tvNoMeja, tvStatus, tvJumlah, tvNamaMenu, tvCatatan;
    Button btnProses, btnSelesai;
    String updateId, updateStatus;

    public DetailPesananPage (String id, String noMeja, String status, String namaMenu, String jumlahPesanan, String catatan){
        this.id = id;
        this.noMeja = noMeja;
        this.status = status;
        this.namaMenu = namaMenu;
        this.jumlahPesanan = jumlahPesanan;
        this.catatan = catatan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_proses_fragment, null,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

        btnProses = view.findViewById(R.id.btnProsesDetail);
        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateId = id;
                updateStatus = tvStatus.getText().toString();
                updateProsesData();
            }
        });
        btnSelesai = view.findViewById(R.id.btnSelesaiDetail);
        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateId = id;
                updateStatus = tvStatus.getText().toString();
                updateSelesaiData();
            }
        });
    }

    private void init(View view) {

        tvNoMeja = view.findViewById(R.id.no_meja);
        tvStatus = view.findViewById(R.id.tv_status);
        tvJumlah = view.findViewById(R.id.tv_jumlahPesanan);
        tvNamaMenu = view.findViewById(R.id.tv_namaMenu);
        tvCatatan = view.findViewById(R.id.tv_catatan);

        tvNoMeja.setText(noMeja);
        tvStatus.setText(status);
        tvJumlah.setText(jumlahPesanan);
        tvNamaMenu.setText(namaMenu);
        tvCatatan.setText(catatan);
    }
    private void updateSelesaiData(){
        final ApiInterface ardData = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseModel> updateSelesaiData = ardData.ardUpdateData(id, "Selesai");
        try {
            showDialog(true);
            updateSelesaiData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    showDialog(false);
                    int kode = response.body().getSuccess();
                    String message = response.body().getMessage();
                    Intent intent = new Intent(getContext(), HomeActivity.class);
                    startActivity(intent);
                    getActivity().finish();
//                    Toast.makeText(getActivity(), "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    showMessage("Gagal Mengupdate Data");
                    showDialog(false);
                }
            });
        }catch (Exception e){
            showDialog(false);
            showMessage("Sebuah Error Terjadi" + e.getLocalizedMessage());
        }
    }

    private void updateProsesData(){
        final ApiInterface ardData = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseModel> updateProsesData = ardData.ardUpdateData(id, "Sedang Diproses");
        try {
            showDialog(true);
            updateProsesData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    showDialog(false);
                    int kode = response.body().getSuccess();
                    String message = response.body().getMessage();
                    Intent intent = new Intent(getContext(), HomeActivity.class);
                    startActivity(intent);
                    getActivity().finish();
//                    Toast.makeText(getActivity(), "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    showMessage("Gagal Mengupdate Data");
                    showDialog(false);
                }
            });
        }catch (Exception e){
            showDialog(false);
            showMessage("Sebuah Error Terjadi" + e.getLocalizedMessage());
        }
    }

}
