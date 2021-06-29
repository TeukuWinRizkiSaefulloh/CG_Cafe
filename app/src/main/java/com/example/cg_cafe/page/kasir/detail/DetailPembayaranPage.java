package com.example.cg_cafe.page.kasir.detail;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cg_cafe.R;
import com.example.cg_cafe.api.ApiClient;
import com.example.cg_cafe.api.ApiInterface;
import com.example.cg_cafe.model.ResponseModel;
import com.example.cg_cafe.page.home.HomeActivity;
import com.example.cg_cafe.page.kasir.KasirPage;
import com.example.cg_cafe.page.process.ProcessPage;
import com.example.cg_cafe.page.process.detail.DetailPesananActivity;
import com.example.cg_cafe.utils.BaseFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPembayaranPage extends BaseFragment {
    String noMeja, id, totalPembayaran, status, namaMenu, jumlahPesanan, harga;
    TextView tvId, tvNoMeja, tvNamaMenu, tvJumlahPesanan, tv_harga, tvTotalHarga;
    Button btnBatalkan, btnBayar;
    String updateId, updateStatus;

    public DetailPembayaranPage(String id, String noMeja, String totalPembayaran, String status, String namaMenu, String jumlahPesanan, String harga){
        this.id = id;
        this.noMeja = noMeja;
        this.namaMenu = namaMenu;
        this.jumlahPesanan = jumlahPesanan;
        this.harga = harga;
        this. totalPembayaran = totalPembayaran;
        this.status = status;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_kasir_fragment, null,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

        btnBayar = getView().findViewById(R.id.btnBayar);
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateId = id;
                updateStatus = status;
                updateBayar();
            }
        });
        btnBatalkan = getView().findViewById(R.id.btnBatalkan);
        btnBatalkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void init(View view) {

        tvId = view.findViewById(R.id.id_pembayaran);
        tvNoMeja = view.findViewById(R.id.no_meja);
        tvTotalHarga = view.findViewById(R.id.tv_totalhargaPembayaran);
        tv_harga = view.findViewById(R.id.tv_harga);
        tvJumlahPesanan = view.findViewById(R.id.tv_jumlahPesanan);
        tvNamaMenu = view.findViewById(R.id.tv_namaMenu);

        tvId.setText(id);
        tvNoMeja.setText(noMeja);
        tvNamaMenu.setText(namaMenu);
        tvJumlahPesanan.setText(jumlahPesanan);
        tv_harga.setText(harga);
        tvTotalHarga.setText(totalPembayaran);
    }

    private void updateBayar(){
        final ApiInterface ardData = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseModel> updateBayar = ardData.ardUpdateData(id, "Selesai Pembayaran");
        try {
            showDialog(true);
            updateBayar.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    showDialog(false);
                    Intent intent = new Intent(getContext(), HomeActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    showMessage("Pembayaran Berhasil");
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
