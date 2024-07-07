package com.BayuNurIndraJatiIrawan;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.BayuNurIndraJatiIrawan.database.LoginDatabase;
import com.BayuNurIndraJatiIrawan.entity.LoginEntity;

import java.util.List;

public class DatabaseLoginActivity extends AppCompatActivity {

    private LoginDatabase db;
    private TextView loginDataTextView;
    private EditText etUsernameToDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_login);

        loginDataTextView = findViewById(R.id.loginDataTextView);
        etUsernameToDelete = findViewById(R.id.etUsernameToDelete);
        db = LoginDatabase.getDatabase(getApplicationContext());

        loadLoginData();
    }

    private void loadLoginData() {
        new Thread(() -> {
            List<LoginEntity> loginEntities = db.loginDao().getAllLogins();
            runOnUiThread(() -> {
                StringBuilder data = new StringBuilder();
                for (LoginEntity login : loginEntities) {
                    data.append("Username: ").append(login.getUsername())
                            .append("\nPassword: ").append(login.getPassword()).append("\n\n");
                }
                loginDataTextView.setText(data.toString());
            });
        }).start();
    }

    public void deleteByUsername(View view) {
        String usernameToDelete = etUsernameToDelete.getText().toString().trim();

        // Validasi input username
        if (usernameToDelete.isEmpty()) {
            Toast.makeText(this, "Masukkan username yang ingin dihapus", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tampilkan dialog konfirmasi sebelum menghapus data
        new AlertDialog.Builder(this)
                .setTitle("Peringatan")
                .setMessage("Apakah Anda yakin ingin menghapus data ini?")
                .setPositiveButton("Ya", (dialog, which) -> {
                    // Hapus data berdasarkan username
                    new Thread(() -> {
                        LoginEntity loginToDelete = db.loginDao().findByUsername(usernameToDelete);
                        if (loginToDelete != null) {
                            db.loginDao().delete(loginToDelete);
                            runOnUiThread(() -> {
                                Toast.makeText(this, "Data dengan username " + usernameToDelete + " berhasil dihapus", Toast.LENGTH_SHORT).show();
                                loadLoginData(); // Refresh tampilan setelah penghapusan data
                            });
                        } else {
                            runOnUiThread(() -> {
                                Toast.makeText(this, "Data dengan username " + usernameToDelete + " tidak ditemukan", Toast.LENGTH_SHORT).show();
                            });
                        }
                    }).start();
                })
                .setNegativeButton("Tidak", null)
                .show();
    }
}