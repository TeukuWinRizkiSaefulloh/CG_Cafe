package com.example.cg_cafe.page.history;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cg_cafe.Adapter.AdapterDataHistory;
import com.example.cg_cafe.R;
import com.example.cg_cafe.api.ApiClient;
import com.example.cg_cafe.api.ApiInterface;
import com.example.cg_cafe.model.DataHistory;
import com.example.cg_cafe.model.HistoryModel;
import com.example.cg_cafe.model.ResponseModel;
import com.example.cg_cafe.utils.BaseFragment;
import com.example.cg_cafe.utils.FragmentInterfaces;
import com.example.cg_cafe.utils.Preferences;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPage extends BaseFragment implements FragmentInterfaces {
    private RecyclerView rvdata;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private HistoryModel historyModel;
    private List<DataHistory> listPesanan = new ArrayList<>();
    private int pendatapanCount = 0;
    private FragmentInterfaces fi;
    public TextView totalPendapatan;

    public HistoryPage() {
        // Required empty public constructor
    }

    public static HistoryPage newInstance() {
        HistoryPage fragment = new HistoryPage();

        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    public void retrieveData(View view){
        final ApiInterface ardData = ApiClient.getClient().create(ApiInterface.class);
        Call<HistoryModel> tampilData = ardData.ardHistoryData();

        tampilData.enqueue(new Callback<HistoryModel>() {
            @Override
            public void onResponse(Call<HistoryModel> call, Response<HistoryModel> response) {
//                Toast.makeText(getActivity(), "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                listPesanan = response.body().getData();
                adData = new AdapterDataHistory(getActivity(), listPesanan);
                rvdata.setAdapter(adData);
                adData.notifyDataSetChanged();

                for(int i = 0; i < listPesanan.size(); i ++){
                    pendatapanCount += Integer.parseInt(listPesanan.get(i).getSub_total());
                }
                totalPendapatan = view.findViewById(R.id.totalpendapatanhistoryFragment);
                if(pendatapanCount != 0){
                    NumberFormat formatter = new DecimalFormat("#,###");
                    double harga = pendatapanCount;
                    String sub_harga = formatter.format(harga);
                    totalPendapatan.setText("Rp. "+String.valueOf(sub_harga) + ",-");
                }
            }

            @Override
            public void onFailure(Call<HistoryModel> call, Throwable t) {
                showMessage("Gagal Menyambung ke server");
            }
        });
    }

    private void init(View view){
        rvdata = view.findViewById(R.id.rv_data_history);
        lmData = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvdata.setLayoutManager(lmData);

        Preferences pref = new Preferences();
        int total = pref.getTotalPendapatan();
        Log.e("MASUK", String.valueOf(pendatapanCount));
//        if(total != 0){
//            totalPendapatan.setText(pendatapanCount);
//        }else{
//            totalPendapatan.setText("Belum ada pendapatan");
//        }

        retrieveData(view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_page, container, false);
    }

    @Override
    public void storeTotalPendapatan(int total) {
        Log.e("MASUK INTERFACE", String.valueOf(total));
    }
}