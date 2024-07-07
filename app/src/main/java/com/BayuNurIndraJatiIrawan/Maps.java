package com.BayuNurIndraJatiIrawan;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Maps extends AppCompatActivity {
    Button btYes, btNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        btYes = findViewById(R.id.btYes);
        btNo = findViewById(R.id.btNo);

        btYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Panggil method untuk membuka intent Maps
                openMaps();
            }
        });

        btNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kembali ke menu sebelumnya
                finish();
                Toast.makeText(Maps.this, "Anda akan kembali ke menu sebelumnya", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openMaps() {
        // Intent untuk membuka aplikasi peta
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setPackage("com.google.android.apps.maps");

        try {
            startActivity(mapIntent);
        } catch (ActivityNotFoundException e) {
            // Tampilkan pesan jika tidak ada aplikasi peta yang tersedia
            Toast.makeText(this, "Tidak ada aplikasi peta yang ditemukan", Toast.LENGTH_SHORT).show();
        }
    }
}
