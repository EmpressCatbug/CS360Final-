package com.example.cs3605_3project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class AccountSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
    }

    public void navigateToSMSPermission(View view) {
        Intent intent = new Intent(this, SMSPermissionActivity.class);
        startActivity(intent);
    }
}
