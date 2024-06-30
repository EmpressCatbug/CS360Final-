package com.example.cs3605_3project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView textWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        textWelcome = findViewById(R.id.textWelcome);

        String username = getIntent().getStringExtra("username");
        textWelcome.setText(String.format("Welcome, %s", username));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case 1000019:
                        navigateToAccountSettings();
                        return true;
                    case 1000006:
                        navigateToSMSPermission();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public void navigateToWeightEntry(View view) {
        Intent intent = new Intent(this, WeightEntryActivity.class);
        startActivity(intent);
    }

    public void navigateToWeightDisplay(View view) {
        Intent intent = new Intent(this, DataDisplayActivity.class);
        //intent.putExtra("date", getCurrentDate());
        //intent.putExtra("weight", getEnteredWeight());
        startActivity(intent);
    }

    public void navigateToGoalWeight(View view) {
        Intent intent = new Intent(this, GoalWeightActivity.class);
        startActivity(intent);
    }

    private void navigateToAccountSettings() {
        Intent intent = new Intent(this, AccountSettingsActivity.class);
        startActivity(intent);
    }
    public void navigateToAccountSettings(View view) {
        Intent intent = new Intent(this, AccountSettingsActivity.class);
        startActivity(intent);
    }
    private void navigateToSMSPermission() {
        Intent intent = new Intent(this, SMSPermissionActivity.class);
        startActivity(intent);
    }


}