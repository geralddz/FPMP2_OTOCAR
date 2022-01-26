package com.example.otocar.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.otocar.Fragment.CheckoutFragment;
import com.example.otocar.Fragment.HistoryFragment;
import com.example.otocar.Fragment.HomeFragment;
import com.example.otocar.Fragment.PaymentFragment;
import com.example.otocar.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Session session;
    Toolbar toolbar;
    TextView tvnama;
    SharedPreferences preferences;
    static FragmentManager fragmentManager;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomnav);
        toolbar = findViewById(R.id.toolbar2);
        tvnama = findViewById(R.id.tvuser);
        session = new Session(this);
        fragmentManager = getSupportFragmentManager();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        preferences = getSharedPreferences("User", 0);
        tvnama.setText(preferences.getString("user", ""));

        if(getIntent().getIntExtra("fragmentNumber",0)==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PaymentFragment()).commit();
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.menu:
                    fragment = new HomeFragment();
                    break;
                case R.id.chekout:
                    fragment = new CheckoutFragment();
                    break;
                case R.id.history:
                    fragment = new HistoryFragment();
                    break;
                case R.id.payment:
                    fragment = new PaymentFragment();
                    break;
            }
            assert fragment != null;
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Peringatan !!! ")
                    .setMessage("Apakah Anda Ingin Keluar ? ")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        session.setLoggedin(false);
                        finish();
                        startActivity(new Intent(this,SignIn.class));
            Toast.makeText(this, "LOG-OUT Berhasil!!!", Toast.LENGTH_SHORT).show();
                    }).setNegativeButton("No", (dialog, which) -> dialog.cancel()).show();
        }else if(item.getItemId() == R.id.profile) {
            startActivity(new Intent(this, Profile.class));
//            fragmentManager.beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
        }
        return true;
    }

}