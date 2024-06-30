package com.example.cs3605_3project2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class WeightEntryActivity extends AppCompatActivity {

    private EditText weightInput;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_entry); // Create this layout file

        weightInput = findViewById(R.id.weightInput);
        dbHelper = new DatabaseHelper(this);
    }

    public void submitWeight(View view) {
        String weight = weightInput.getText().toString();

        if (weight.isEmpty()) {
            Toast.makeText(this, "Please enter your weight", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_DATE, System.currentTimeMillis());
        values.put(DatabaseHelper.COLUMN_WEIGHT, Double.parseDouble(weight));

        long newRowId = db.insert(DatabaseHelper.TABLE_WEIGHTS, null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Weight submitted: " + weight, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Error saving weight", Toast.LENGTH_SHORT).show();
        }
    }
}