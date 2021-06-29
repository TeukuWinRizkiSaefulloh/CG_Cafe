package com.example.cg_cafe.page.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cg_cafe.R;
import com.example.cg_cafe.utils.BaseFragment;
import com.example.cg_cafe.utils.SessionManager;

public class ProfilePage extends BaseFragment {

    SessionManager sessionManager;
    String nama, jabatan, email, no_hp;
    TextView tvName, tvJabatan, tvEmail, tvNohp;

    public ProfilePage() {
        // Required empty public constructor
    }


    public static ProfilePage newInstance(String param1, String param2) {
        ProfilePage fragment = new ProfilePage();
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

    private void init(View view) {
        sessionManager = new SessionManager(getActivity());
        tvName = view.findViewById(R.id.tv_name);
        tvJabatan = view.findViewById(R.id.tv_jabatan);
        tvEmail = view.findViewById(R.id.txEmail);
        tvNohp = view.findViewById(R.id.txPhone);

        nama = sessionManager.getUserDetail().get(SessionManager.NAMA);
        jabatan = sessionManager.getUserDetail().get(SessionManager.JABATAN);
        email = sessionManager.getUserDetail().get(SessionManager.EMAIL);
        no_hp = sessionManager.getUserDetail().get(SessionManager.NO_HP);


        tvName.setText(nama);
        tvJabatan.setText(jabatan);
        tvEmail.setText(email);
        tvNohp.setText(no_hp);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_page, container, false);
    }
}