package com.example.cs3605_3project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class DataAdapter extends BaseAdapter {

    private List<DataItem> dataList;
    private Context context;
    private DatabaseHelper dbHelper;

    public DataAdapter(List<DataItem> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
        this.dbHelper = new DatabaseHelper(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_data_row, parent, false);
        }

        TextView textViewDate = convertView.findViewById(R.id.textViewDate);
        TextView textViewWeight = convertView.findViewById(R.id.textViewWeight);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        DataItem dataItem = dataList.get(position);
        textViewDate.setText(dataItem.getDate());
        textViewWeight.setText(dataItem.getWeight());

        buttonDelete.setVisibility(View.VISIBLE);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteWeight(dataItem.getId());
                dataList.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
