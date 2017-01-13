package com.example.akanshugupta9.pomoma;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by akanshugupta9 on 13/1/17.
 */

public class FourthFragment extends Fragment {

    ListView lv;
    Context context;

    ArrayList prgmName;
    public static String [] amountList={"+100", "-200", "-150", "+500", "-120", "+700", "-500"};
    public static String [] summaryList={"Bought Something.", "Went to Movie.", "Recieved from home.", "Amazon gifted.", "No idea where it came from.", "Donation.", "Stolen from my wallet."};
    public static String [] dateList={"1 Jan 2017", "12 July 2017", "1 Dec 2017", "21 Jan 2017", "19 Mar 2017", "13 Jan 2017", "5 Feb 2017"};
    public static String [] categoryList={"To Spendable", "From Non-Spendable", "To Spendable", "From Spendable", "From Spendable", "From Spendable", "To Non-Spendable"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fourth_frag, container, false);

        context=getContext();

        lv=(ListView) v.findViewById(R.id.listView);
        lv.setAdapter(new HistoryCustomAdapter((MainActivity) getActivity(), amountList, categoryList, summaryList, dateList));

        return v;
    }

    public static FourthFragment newInstance(String text) {

        FourthFragment f = new FourthFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}