package com.example.otocar.Activity;

import static android.content.ContentValues.TAG;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.otocar.R;

import java.util.concurrent.Executor;

public class Fingerprint extends AppCompatActivity {
    TextView titel;
    ImageView back, FP;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);
        back = findViewById(R.id.back_icon);
        titel = findViewById(R.id.title);
        FP = findViewById(R.id.Fp);
        titel.setText("Verifikasi");

        back.setOnClickListener(v -> {
            Intent i = new Intent(this, Home.class );
            i.putExtra("fragmentNumber",1);
            startActivity(i);
        });

        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BIOMETRIC_STRONG)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.e("MY_APP_TAG", "No biometric features available on this device.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.");
                break;
        }
        promptInfo = new androidx.biometric.BiometricPrompt.PromptInfo.Builder()
                .setTitle("Verifikasi Pembayaran")
                .setNegativeButtonText("Cancel")
                .build();

        executor = ContextCompat.getMainExecutor(this);
        final BiometricPrompt myBiometricPrompt = new BiometricPrompt(this, executor,
                new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Log.d(TAG, "Fingerprint recognised successfully");
                Toast.makeText(getApplicationContext(), "Verifikasi Pembayaran Berhasil", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.d(TAG, "Fingerprint not recognised");
                Toast.makeText(getApplicationContext(), "Coba Lagi", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                } else {
                    Log.d(TAG, "An unrecoverable error occurred");
                }
            }
        });
        FP.setOnClickListener(v -> {
            myBiometricPrompt.authenticate(promptInfo);
        });
    }
}