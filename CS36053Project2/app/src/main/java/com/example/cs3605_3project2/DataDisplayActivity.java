package com.example.cs3605_3project2;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import android.content.Intent;

public class DataDisplayActivity extends AppCompatActivity {

    private ListView listViewData;
    private DataAdapter dataAdapter;
    private List<DataItem> dataList;
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        listViewData = findViewById(R.id.listViewData);

        dbHelper = new DatabaseHelper(this);
        listViewData = findViewById(R.id.listViewData);

        dataList = dbHelper.getAllWeights();
        dataAdapter = new DataAdapter(dataList, this);
        listViewData.setAdapter(dataAdapter);

    }
    public void navigateToEnterWeight(View view) {
        Intent intent = new Intent(this, WeightEntryActivity.class);
        startActivity(intent);
    }
}