package com.example.cs3605_3project2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "weightTracker.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_WEIGHTS = "weights";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_WEIGHT = "weight";

    public static final String TABLE_GOAL_WEIGHT = "goal_weight";
    public static final String COLUMN_GOAL_WEIGHT = "goal_weight";

    private static final String TABLE_CREATE_WEIGHTS =
            "CREATE TABLE " + TABLE_WEIGHTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_WEIGHT + " REAL);";

    private static final String TABLE_CREATE_GOAL_WEIGHT =
            "CREATE TABLE " + TABLE_GOAL_WEIGHT + " (" +
                    COLUMN_GOAL_WEIGHT + " REAL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_WEIGHTS);
        db.execSQL(TABLE_CREATE_GOAL_WEIGHT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEIGHTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOAL_WEIGHT);
        onCreate(db);
    }

    public List<DataItem> getAllWeights() {
        List<DataItem> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_WEIGHTS, new String[]{COLUMN_ID, COLUMN_DATE, COLUMN_WEIGHT}, null, null, null, null, COLUMN_DATE + " DESC");

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
                long dateMillis = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATE));
                double weight = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_WEIGHT));
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(dateMillis));
                dataList.add(new DataItem(id, date, String.valueOf(weight) + " lbs"));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dataList;
    }

    public void deleteWeight(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WEIGHTS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}