package com.example.otocar.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.otocar.Data.DAO.UserDAO;
import com.example.otocar.Data.Database.MyApp;
import com.example.otocar.Data.Model.UserEntity;
import com.example.otocar.R;

public class Registrasi extends AppCompatActivity {
    ImageView back;
    TextView titel;
    EditText username, pass, konfirm;
    Button register;
    private UserDAO userDAO;
    private Session session;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        username = findViewById(R.id.Username);
        pass = findViewById(R.id.Pass);
        konfirm = findViewById(R.id.konfirm);
        register = findViewById(R.id.Registrasi);
        session = new Session(this);
        back = findViewById(R.id.back_icon);
        titel = findViewById(R.id.title);

        if(session.loggedin()){
            startActivity(new Intent(this,Home.class));
            finish();
        }

        titel.setText("Registrasi");
        back.setOnClickListener(v -> {
            Intent i = new Intent(this, SignIn.class );
            startActivity(i);
        });
        userDAO = MyApp.db.userDAO();
        register.setOnClickListener(v -> {
            String user = username.getText().toString().trim();
            String passw = pass.getText().toString().trim();
            String konfirmasi = konfirm.getText().toString().trim();

            if (passw.length() == 0 && (konfirmasi.length() == 0)) {
                username.setError("Password Tidak Boleh Kosong");
            }
            else if (!passw.equals(konfirmasi)) {
                konfirm.setError("Konfirmasi Password Anda Salah");
            }
            else {
                UserEntity userEntity = new UserEntity(user, passw);
                userDAO.Register(userEntity);
                session.setLoggedin(true);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("user", user);
                editor.apply();
                Toast.makeText(Registrasi.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, Home.class );
                startActivity(i);
            }
        });
    }
}