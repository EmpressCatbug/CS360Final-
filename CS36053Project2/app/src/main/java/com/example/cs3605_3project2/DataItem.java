package com.example.cs3605_3project2;

public class DataItem {
    private long id;
    private String date;
    private String weight;

    public DataItem(long id, String date, String weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getWeight() {
        return weight;
    }
}
