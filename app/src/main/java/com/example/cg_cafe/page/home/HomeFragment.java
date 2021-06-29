package com.example.cg_cafe.page.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.example.cg_cafe.Adapter.AdapterDataPemesanan;
import com.example.cg_cafe.R;
import com.example.cg_cafe.api.ApiClient;
import com.example.cg_cafe.api.ApiInterface;
import com.example.cg_cafe.model.DataPemesanan;
import com.example.cg_cafe.model.PemesananModel;
import com.example.cg_cafe.page.history.HistoryPage;
import com.example.cg_cafe.utils.BaseFragment;
import com.example.cg_cafe.utils.SessionManager;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {

    SessionManager sessionManager;
    private RecyclerView rvdata;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataPemesanan> listPemesanan = new ArrayList<>();


    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        init(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

//    private void init(View view){
//        sessionManager = new SessionManager(getActivity());
//        rvdata = view.findViewById(R.id.rv_data_order);
//        lmData = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        rvdata.setLayoutManager(lmData);
//        sessionManager.storeStringData("pesananPage", "pesananPage");
//        retrieveData();

//    }

//    public void retrieveData(){
//        final ApiInterface ardData = ApiClient.getClient().create(ApiInterface.class);
//        Call<PemesananModel> tampilData = ardData.ardPemesananData();
//
//        try {
//            showDialog(true);
//            tampilData.enqueue(new Callback<PemesananModel>() {
//                @Override
//                public void onResponse(Call<PemesananModel> call, Response<PemesananModel> response) {
//                    showDialog(false);
//                    int success = response.body().getSuccess();
//                    String message = response.body().getMessage();
//
////                    Toast.makeText(getActivity(), "Success : "+success+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
//
//                    listPemesanan = response.body().getData();
//                    adData = new AdapterDataPemesanan(getActivity(), listPemesanan);
//                    rvdata.setAdapter(adData);
//                    adData.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onFailure(Call<PemesananModel> call, Throwable t) {
//                    showMessage("Gagal Menghubung ke server");
//                    showDialog(false);
//                }
//            });
//        }catch (Exception e){
//            showDialog(false);
//            showMessage("Sebuah Error Terjadi" + e.getLocalizedMessage());
//        }
//    }
}