package com.BayuNurIndraJatiIrawan;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Camera extends AppCompatActivity {
    static final int CAMERA_REQUEST_CODE = 1;
    Button btYes, btNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btYes = findViewById(R.id.btYes);
        btNo = findViewById(R.id.btNo);

        btYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Panggil method untuk memulai intent kamera
                dispatchTakePictureIntent();
            }
        });

        btNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kembali ke menu sebelumnya
                finish();
                Toast.makeText(Camera.this, "Anda akan kembali ke menu sebelumnya", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Tidak ada aplikasi kamera yang ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                // Ambil foto dari data yang dikembalikan oleh aplikasi kamera
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                // Tampilkan atau simpan foto sesuai kebutuhan
                Toast.makeText(this, "Foto berhasil diambil", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
