package com.example.cs3605_3project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    public void login(View view) {
        // Implement login logic
        Intent intent = new Intent(this, com.example.cs3605_3project2.DashboardActivity.class);
        startActivity(intent);
    }

    public void register(View view) {
        // Implement registration logic
    }
}
