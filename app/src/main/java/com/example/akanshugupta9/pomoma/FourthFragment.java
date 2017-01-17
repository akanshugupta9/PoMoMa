package com.example.akanshugupta9.pomoma;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by akanshugupta9 on 13/1/17.
 */

public class FourthFragment extends Fragment {

    ListView lv;
    Context context;
    ArrayList<Float> amountList;
    ArrayList<String> summaryList;
    ArrayList<String> dateList;
    ArrayList<Integer> categoryList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fourth_frag, container, false);

        context=getContext();

        try {
            getDataFromDatabase();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        lv=(ListView) v.findViewById(R.id.listView);
        lv.setAdapter(new HistoryCustomAdapter((MainActivity) getActivity(), amountList, categoryList, summaryList, dateList));

        return v;
    }

    public void getDataFromDatabase() throws ParseException {
        DBHelper dbHelper = new DBHelper(getContext());
        TransactionData td = dbHelper.getData();
        amountList = td.amount;
        summaryList = td.summary;
        dateList = td.date;
        categoryList = td.type;
    }

    public static FourthFragment newInstance(String text) {

        FourthFragment f = new FourthFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}