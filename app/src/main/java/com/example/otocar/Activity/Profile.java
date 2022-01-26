package com.example.otocar.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.otocar.R;

public class Profile extends AppCompatActivity {
    TextView titel, nama, alamat, nope, email, user;
    ImageView back;
    Button edit;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        preferences = getSharedPreferences("User", 0);

        user = findViewById(R.id.tvUser);
        nama = findViewById(R.id.tvnamalkp);
        alamat = findViewById(R.id.tvalmt);
        nope = findViewById(R.id.tvHP);
        email = findViewById(R.id.tvemail);

        user.setText(preferences.getString("user", ""));
        nama.setText(preferences.getString("Nama", ""));
        email.setText(preferences.getString("Email", ""));
        nope.setText(preferences.getString("No. HP", ""));
        alamat.setText(preferences.getString("Alamat", ""));

        back = findViewById(R.id.back_icon);
        titel = findViewById(R.id.title);
        edit = findViewById(R.id.bteditprofile);
        titel.setText("Profile");

        back.setOnClickListener(v -> {
            Intent i = new Intent(this, Home.class );
            i.putExtra("fragmentNumber",0);
            startActivity(i);
        });

        edit.setOnClickListener(v -> {
            Intent i = new Intent(this, EditProfile.class );
            startActivity(i);
        });
    }
}