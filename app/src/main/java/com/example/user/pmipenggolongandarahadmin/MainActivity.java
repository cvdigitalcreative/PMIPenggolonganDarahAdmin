package com.example.user.pmipenggolongandarahadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Button btnHome;
    private Button btnstokDarah;
    private Button btnpendonorDarah;
    private Button btninformasiEvent;
    private Button btntambahPendonor;
    private Button btnLaporan;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHome = (Button)findViewById(R.id.home);
        btnstokDarah = (Button)findViewById(R.id.stok_darah);
        btnpendonorDarah = (Button)findViewById(R.id.pendonor_darah);
        btninformasiEvent = (Button)findViewById(R.id.informasi_event);
        btntambahPendonor = (Button)findViewById(R.id.tambah_pendonor);
        btnLaporan = (Button)findViewById(R.id.laporan);
        btnLogout = (Button)findViewById(R.id.logout);

        isLoginCheck();
        
        HomeClick();
        StokDarahClick();
        PendonorDarahClick();
        InformasiEventClick();
        TambahPendonorClick();
        LaporanClick();
        LogoutClick();
    }

    private void LogoutClick() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void LaporanClick() {
        btnLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LaporanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void TambahPendonorClick() {
        btntambahPendonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TambahPendonorActivity.class);
                startActivity(intent);
            }
        });
    }

    private void InformasiEventClick() {
        btninformasiEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InformasiEventActivity.class);
                startActivity(intent);
            }
        });
    }

    private void PendonorDarahClick() {
        btnpendonorDarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PendonorDarahActivity.class);
                startActivity(intent);
            }
        });
    }

    private void StokDarahClick() {
        btnstokDarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StokDarahActivity.class);
                startActivity(intent);
            }
        });
    }

    private void HomeClick() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void isLoginCheck() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user == null){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
