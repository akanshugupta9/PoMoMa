package com.example.akanshugupta9.pomoma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by akanshugupta9 on 13/1/17.
 */

public class HistoryCustomAdapter extends BaseAdapter {
    String [] amountList, dateList, summaryList, categoryList;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public HistoryCustomAdapter(MainActivity mainActivity, String[] amountList, String[] categoryList, String[] summaryList, String[] dateList) {
        // TODO Auto-generated constructor stub
        this.categoryList=categoryList;
        this.amountList=amountList;
        this.dateList=dateList;
        this.summaryList=summaryList;
        context=mainActivity;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return categoryList.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView amountTv, summaryTv, dateTv, categoryTv;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.history_list, null);
        holder.amountTv=(TextView) rowView.findViewById(R.id.amount);
        holder.summaryTv=(TextView) rowView.findViewById(R.id.summary);
        holder.dateTv=(TextView) rowView.findViewById(R.id.date);
        holder.categoryTv=(TextView) rowView.findViewById(R.id.category);
        holder.amountTv.setText(amountList[position]);
        holder.summaryTv.setText(summaryList[position]);
        holder.dateTv.setText(dateList[position]);
        holder.categoryTv.setText(categoryList[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+summaryList[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}