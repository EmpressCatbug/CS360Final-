package com.example.cs3605_3project2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SMSPermissionActivity extends AppCompatActivity {

    private static final int SMS_PERMISSION_CODE = 100;
    private TextView textViewStatus;
    private Button buttonRequestPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_permission);

        textViewStatus = findViewById(R.id.textViewSMSPermission);
        buttonRequestPermission = findViewById(R.id.buttonRequestSMSPermission);

        checkSMSPermission();
    }
    private void checkSMSPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            textViewStatus.setText("SMS permission not granted.");
            buttonRequestPermission.setVisibility(View.VISIBLE);
        } else {
            textViewStatus.setText("SMS permission granted.");
            buttonRequestPermission.setVisibility(View.GONE);
            handleSMSPermissionGranted();
        }
    }
    public void requestSMSPermission(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        } else {
            // Permission already granted
            handleSMSPermissionGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                handleSMSPermissionGranted();
            } else {
                handleSMSPermissionDenied();
            }
        }
    }

    private void handleSMSPermissionGranted() {
        textViewStatus.setText("SMS permission granted.");
        Toast.makeText(this, "SMS permission granted.", Toast.LENGTH_SHORT).show();
        sendAutomatedSMS();
    }

    private void handleSMSPermissionDenied() {
        textViewStatus.setText("SMS permission denied.");
        Toast.makeText(this, "SMS permission denied.", Toast.LENGTH_SHORT).show();
    }

    private void sendAutomatedSMS() {
        // Example logic to send an automated SMS notification based on a specific condition
        String phoneNumber = "1234567890"; // Replace with the target phone number
        String message = "This is an automated notification based on your app settings.";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send SMS", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
