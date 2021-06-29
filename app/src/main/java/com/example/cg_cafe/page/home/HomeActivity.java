package com.example.cg_cafe.page.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.cg_cafe.R;
import com.example.cg_cafe.page.login.LoginActivity;
import com.example.cg_cafe.page.history.HistoryPage;
import com.example.cg_cafe.page.kasir.KasirPage;
import com.example.cg_cafe.page.order.OrderPage;
import com.example.cg_cafe.page.process.ProcessPage;
import com.example.cg_cafe.page.profile.ProfilePage;
import com.example.cg_cafe.utils.BaseActivity;
import com.example.cg_cafe.utils.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends BaseActivity {
    SessionManager sessionManager;
    FrameLayout fl;
    ImageView backButton;
    TextView headerText, tvHeaderName, tvHeaderJabatan;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    String nama, jabatan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_layout);

//        ===== binding =====
        fl = findViewById(R.id.containerFrame);
        backButton = findViewById(R.id.backButton);
        headerText = findViewById(R.id.containerName);
        bottomNavigationView = findViewById(R.id.bottomNavHome);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
//        ====== end ======
        loadFragment(new HomeFragment(), fl);
        sessionManager = new SessionManager(HomeActivity.this);

        View headerView = navigationView.getHeaderView(0);
        tvHeaderName = headerView.findViewById(R.id.tvHeaderName);
        tvHeaderJabatan = headerView.findViewById(R.id.tvHeaderJabatan);
        nama = sessionManager.getUserDetail().get(SessionManager.NAMA);
        jabatan = sessionManager.getUserDetail().get(SessionManager.JABATAN);
        tvHeaderName.setText(nama);
        tvHeaderJabatan.setText(jabatan);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId()){
                    case R.id.kasirMenu:
                        selectedFragment = new KasirPage();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        headerText.setText("Kasir");
                        break;
                    case R.id.proccessMenu:
                        selectedFragment = new ProcessPage();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        headerText.setText("Proses Pemesanan");
                        break;
                    case R.id.orderMenu:
                        selectedFragment = new OrderPage();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        headerText.setText("Pemesanan");
                        break;
                    case R.id.nav_logout:
                        sessionManager.logoutSession();
                        moveToLogin();
                        break;
                }

                loadFragment(selectedFragment, fl);

                return true;
            }

        });
        headerText.setText("Home");
//        drawerLayout.closeDrawer(Gravity.LEFT);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;

            switch (item.getItemId()){

                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    headerText.setText("Home");
                    loadFragment(selectedFragment, fl);
                    break;

                case R.id.nav_profile:
                    selectedFragment = new ProfilePage();
                    headerText.setText("Profile");
                    loadFragment(selectedFragment, fl);
                    break;
                case R.id.historyMenu:
                    selectedFragment = new HistoryPage();
                    headerText.setText("History Transaksi");
                    loadFragment(selectedFragment, fl);
                    break;
            }
            return true;
        }
    };

    public void moveToLogin() {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

}
