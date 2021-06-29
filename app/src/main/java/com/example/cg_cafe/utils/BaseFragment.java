package com.example.cg_cafe.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    AlertDialog.Builder builder;
    Dialog pd;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() != null){
            builder = new AlertDialog.Builder(getActivity());
            pd = new Dialog(getActivity());
        }
    }

    public void showDialog(Boolean show){
        if(getActivity() != null){
            if(null != pd){
                if(show){
                    pd.show();
                }else{
                    pd.dismiss();
                }
                pd.setCancelable(false);
            }
        }
    }

    public void showMessage(String message){
        if(getActivity() != null){
            builder.setMessage(message)
                    .setTitle("Cafe Gue")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
        }
    }

}
