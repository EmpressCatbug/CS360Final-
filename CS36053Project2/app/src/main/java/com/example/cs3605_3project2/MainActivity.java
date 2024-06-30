package com.example.cs3605_3project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Create this layout file

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    public void login(View view) {
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if (user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Proceed to DashboardActivity
        Intent intent = new Intent(this, com.example.cs3605_3project2.DashboardActivity.class);
        intent.putExtra("username", user);
        startActivity(intent);
    }

    public void register(View view) {
        // Implement registration logic
    }
}