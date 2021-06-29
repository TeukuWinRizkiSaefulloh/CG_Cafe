package com.example.cg_cafe.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.cg_cafe.model.User;

import java.util.HashMap;


public class SessionManager {

    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String KARYAWAN_ID = "karyawan_id";
    public static final String EMAIL = "email";
    public static final String NAMA = "nama";
    public static final String JABATAN = "jabatan";
    public static final String NO_HP = "no_hp";

    public SessionManager(Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void storeStringData(String name, String data){
        editor.putString(name, data);
        editor.apply();
        editor.commit();
    }

    public void storeBooleanData(String name, boolean data){
        editor.putBoolean(name, data);
        editor.apply();
        editor.commit();
    }

    public void createLoginSession(User user){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(EMAIL, user.getEmail());
        editor.putString(NAMA, user.getNama());
        editor.putString(JABATAN, user.getJabatan());
        editor.putString(NO_HP, user.getNoHp());
        editor.commit();
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> karyawan = new HashMap<>();
        karyawan.put(KARYAWAN_ID, sharedPreferences.getString(KARYAWAN_ID,null));
        karyawan.put(EMAIL, sharedPreferences.getString(EMAIL,null));
        karyawan.put(NAMA, sharedPreferences.getString(NAMA,null));
        karyawan.put(JABATAN, sharedPreferences.getString(JABATAN,null));
        karyawan.put(NO_HP, sharedPreferences.getString(NO_HP, null));
        return karyawan;
    }

    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

    public String getStringData(String key){
        return sharedPreferences.getString(key, "");
    }

    public boolean getBooleanData(String key){
        return sharedPreferences.getBoolean(key, false);
    }

}
