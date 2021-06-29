package com.example.cg_cafe.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cg_cafe.R;

public abstract class BaseActivity extends AppCompatActivity {
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
    }

    public void loadFragment(Fragment fragment, View baseFragment){
        fragmentManager.beginTransaction()
                .replace(baseFragment.getId(), fragment)
                .commit();
    }

    public void replaceFragment(Fragment fragment, View baseFrame){
        fragmentManager.beginTransaction()
                .replace(baseFrame.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }

    public void showToast(String message, Context context){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void showDialog(String message, Context context){

    }

}
