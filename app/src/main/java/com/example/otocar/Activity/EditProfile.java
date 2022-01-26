package com.example.otocar.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.otocar.R;

public class EditProfile extends AppCompatActivity {
    TextView titel;
    ImageView back;
    Button simpan;
    EditText email, nama, nope, alamat;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        email = findViewById(R.id.etemail);
        nama = findViewById(R.id.etnamalkp);
        nope = findViewById(R.id.etnope);
        alamat = findViewById(R.id.etalmt);

        back = findViewById(R.id.back_icon);
        titel = findViewById(R.id.title);
        simpan = findViewById(R.id.btsimpan);
        titel.setText("Edit Profile");

        preferences = getSharedPreferences("User", 0);


        back.setOnClickListener(v -> {
            Intent i = new Intent(this, Profile.class );
            startActivity(i);
        });

        simpan.setOnClickListener(v -> {
            String inputnama = nama.getText().toString();
            String inputemail= email.getText().toString();
            String inputhp = nope.getText().toString();
            String inputalmt = alamat.getText().toString();

            if (inputnama.length() == 0 ) {
                nama.setError("Nama Tidak Boleh Kosong");
            }
            else if (inputalmt.length() == 0) {
                alamat.setError("Alamat Tidak Boleh Kosong");
            }
            else if (inputhp.length() == 0){
                nope.setError("No. HP Tidak Boleh Kosong");
            }
            else if (inputemail.length() == 0 && !Patterns.EMAIL_ADDRESS.matcher(inputemail).matches()){
                email.setError("Masukkan email dengan benar");
            }
            else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Email", inputemail);
            editor.putString("Nama", inputnama);
            editor.putString("No. HP", inputhp);
            editor.putString("Alamat", inputalmt);
            editor.apply();
            Toast.makeText(EditProfile.this, "Profile Tersimpan", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EditProfile.this, Profile.class);
            startActivity(intent);
            }
        });
    }
}