package com.example.otocar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.otocar.Data.DAO.UserDAO;
import com.example.otocar.Data.Database.AppUser;
import com.example.otocar.Data.Model.UserEntity;

public class SignIn extends AppCompatActivity {

    EditText user, pass;
    Button masuk, klik;
    UserDAO userDAO;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        user = findViewById(R.id.User);
        pass = findViewById(R.id.pass);
        masuk = findViewById(R.id.masuk);
        klik = findViewById(R.id.klik);
        session = new Session(this);

        if(session.loggedin()){
            startActivity(new Intent(this,Home.class));
            finish();
        }

        userDAO = AppUser.db.userDAO();
        masuk.setOnClickListener(v -> {

            String username = user.getText().toString().trim();
            String password = pass.getText().toString().trim();

            UserEntity userEntity = userDAO.login(username, password);
            if (userEntity != null) {
                session.setLoggedin(true);
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                String name = userEntity.getUsername();
                Intent i = new Intent(this, Home.class)
                        .putExtra("name", name);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(this, "Isikan Username dan Password Dengan Benar", Toast.LENGTH_SHORT).show();
            }
        });

        klik.setOnClickListener(v -> {
            Intent i = new Intent(this, Registrasi.class);
            startActivity(i);
        });
    }
}