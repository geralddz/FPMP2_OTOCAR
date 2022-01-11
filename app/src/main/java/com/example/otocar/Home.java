package com.example.otocar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.otocar.Data.DAO.PaymentDAO;
import com.example.otocar.Data.Database.PaymentsDatabase;
import com.example.otocar.Data.Model.UserEntity;
import com.example.otocar.Fragment.HistoryFragment;
import com.example.otocar.Fragment.HomeFragment;
import com.example.otocar.Fragment.PaymentFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    public static PaymentDAO payDAO;
    BottomNavigationView bottomNavigationView;
    Session session;
    Toolbar toolbar;
    TextView tvnama;
    List<UserEntity> userEntityList = new ArrayList<>();

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottomnav);
        toolbar = findViewById(R.id.toolbar2);
        tvnama = findViewById(R.id.tvuser);
        session = new Session(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        payDAO = Room.databaseBuilder(getApplicationContext(), PaymentsDatabase.class, "paydb").allowMainThreadQueries().build().paymentDAO();

        if(session.loggedin()){
            String name = getIntent().getStringExtra("name");
            tvnama.setText(name);
            session.setLoggedin(true);
        }

        String name = getIntent().getStringExtra("name");
        tvnama.setText(name);
        session.setLoggedin(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.menu:
                    fragment = new HomeFragment();
                    break;

                case R.id.payment:
                    fragment = new PaymentFragment();
                    break;
                case R.id.history:
                    fragment = new HistoryFragment();
                    break;
            }
            assert fragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
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
            session.setLoggedin(false);
            finish();
            startActivity(new Intent(this,SignIn.class));
            Toast.makeText(this, "LOG-OUT Berhasil!!!", Toast.LENGTH_SHORT).show();

        }
        return true;
    }

}