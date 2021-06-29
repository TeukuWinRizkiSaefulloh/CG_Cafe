package com.example.cg_cafe.page.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cg_cafe.R;
import com.example.cg_cafe.model.Login;
import com.example.cg_cafe.model.User;
import com.example.cg_cafe.page.home.HomeActivity;
import com.example.cg_cafe.utils.SessionManager;

import com.example.cg_cafe.api.ApiClient;
import com.example.cg_cafe.api.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail, etPassword;
    Button btnLogin;
    String Email, Password;
    ApiInterface apiInterface;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                Email = etEmail.getText().toString();
                Password = etPassword.getText().toString();
                login(Email,Password);
                break;
        }
    }

    private void login(String email, String password) {
        Log.e("Fungsi Login", email);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall = apiInterface.loginResponse(email, password);
        Log.e("Login", "Masuk sini");
        try {
            loginCall.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    if(response.isSuccessful()){

                        // Ini untuk menyimpan sesi
                        sessionManager = new SessionManager(LoginActivity.this);
                        User user = response.body().getUser();
                        sessionManager.createLoginSession(user);

                        //Ini untuk pindah
                        Toast.makeText(LoginActivity.this, response.body().getUser().getEmail(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                        Log.e("Button Login", response.body().toString());
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Form Input Harus Diisi", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Log.e("Exception", e.getLocalizedMessage());
        }
    }
}