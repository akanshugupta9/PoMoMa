package com.example.akanshugupta9.pomoma;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by akanshugupta9 on 13/1/17.
 */

public class HistoryCustomAdapter extends BaseAdapter {
    ArrayList<String> summaryList;
    ArrayList<String> dateList;
    ArrayList<Integer> categoryList;
    ArrayList<Float> amountList;
    Context context;
    private static LayoutInflater inflater=null;
    public HistoryCustomAdapter(MainActivity mainActivity, ArrayList<Float> amountList, ArrayList<Integer> categoryList, ArrayList<String> summaryList, ArrayList<String> dateList) {
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
        if(amountList!=null)
        return amountList.size();
        else return 0;
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
        if(getCount()!=0) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            String den = prefs.getString("denomination_preference", "Rs.");
            holder.amountTv = (TextView) rowView.findViewById(R.id.amount);
            holder.summaryTv = (TextView) rowView.findViewById(R.id.summary);
            holder.dateTv = (TextView) rowView.findViewById(R.id.date);
            holder.categoryTv = (TextView) rowView.findViewById(R.id.category);
            holder.amountTv.setText(Float.toString(amountList.get(position))+den);
            holder.summaryTv.setText(summaryList.get(position));
            String[] tmp = dateList.get(position).split(" ");
            holder.dateTv.setText(tmp[0]+" "+tmp[1]+" "+tmp[2]);
            int tmp1 = categoryList.get(position);
            String tmp2;
            if(tmp1 == 0){
                tmp2 = "Spendable";
            }else {
                tmp2 = "Non-Spendable";
            }
            if(amountList.get(position) >= 0){
                holder.categoryTv.setText("Added to "+tmp2);
            }else {
                holder.categoryTv.setText("Spent from "+tmp2);
            }
        }
        return rowView;
    }

}