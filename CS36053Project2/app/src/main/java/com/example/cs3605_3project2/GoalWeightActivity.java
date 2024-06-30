package com.example.cs3605_3project2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GoalWeightActivity extends AppCompatActivity {

    private EditText goalWeightInput;
    private TextView currentGoalWeight;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_weight); // Create this layout file

        goalWeightInput = findViewById(R.id.goalWeightInput);
        currentGoalWeight = findViewById(R.id.currentGoalWeight);
        dbHelper = new DatabaseHelper(this);

        loadGoalWeight();
    }

    public void setGoalWeight(View view) {
        String goalWeight = goalWeightInput.getText().toString();

        if (goalWeight.isEmpty()) {
            Toast.makeText(this, "Please enter your goal weight", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_GOAL_WEIGHT, Double.parseDouble(goalWeight));

        // Insert or update the goal weight
        long newRowId = db.update(DatabaseHelper.TABLE_GOAL_WEIGHT, values, null, null);
        if (newRowId == -1) {
            newRowId = db.insert(DatabaseHelper.TABLE_GOAL_WEIGHT, null, values);
        }

        if (newRowId != -1) {
            Toast.makeText(this, "Goal weight set: " + goalWeight, Toast.LENGTH_SHORT).show();
            loadGoalWeight();
        } else {
            Toast.makeText(this, "Error saving goal weight", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadGoalWeight() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_GOAL_WEIGHT,
                new String[]{DatabaseHelper.COLUMN_GOAL_WEIGHT},
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            double goalWeight = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_GOAL_WEIGHT));
            currentGoalWeight.setText("Current Goal Weight: " + goalWeight);
            cursor.close();
        } else {
            currentGoalWeight.setText("No goal weight set");
        }
    }
}