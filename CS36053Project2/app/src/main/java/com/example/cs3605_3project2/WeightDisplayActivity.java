package com.example.cs3605_3project2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class WeightDisplayActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_display); // Create this layout file

        dbHelper = new DatabaseHelper(this);
        ListView weightListView = findViewById(R.id.weightListView);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_WEIGHTS,
                null,
                null,
                null,
                null,
                null,
                DatabaseHelper.COLUMN_DATE + " DESC"
        );

        String[] from = {DatabaseHelper.COLUMN_DATE, DatabaseHelper.COLUMN_WEIGHT};
        int[] to = {R.id.textDate, R.id.textWeight};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.weight_list_item, // Create this layout file for list items
                cursor,
                from,
                to,
                0
        );

        weightListView.setAdapter(adapter);
    }
}