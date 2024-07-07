package com.BayuNurIndraJatiIrawan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.BayuNurIndraJatiIrawan.MenuUtama;
import com.BayuNurIndraJatiIrawan.database.LoginDatabase;
import com.BayuNurIndraJatiIrawan.entity.LoginEntity;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private EditText edUsername, edPassword;
    private LoginDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        edUsername = findViewById(R.id.edusername);
        edPassword = findViewById(R.id.edpass);

        db = LoginDatabase.getDatabase(getApplicationContext());

        button1.setOnClickListener(v -> {
            String username = edUsername.getText().toString();
            String password = edPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Username dan Password harus diisi", Toast.LENGTH_SHORT).show();
            } else {
                saveLoginData(username, password);
                Intent intent1 = new Intent(getApplicationContext(), MenuUtama.class);
                intent1.putExtra("USERNAME", username);
                startActivity(intent1);
            }
        });
    }

    private void saveLoginData(String username, String password) {
        new Thread(() -> {
            LoginEntity loginEntity = new LoginEntity(username, password);
            db.loginDao().insert(loginEntity);
        }).start();
    }
}