package com.example.akanshugupta9.pomoma;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.FloatProperty;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

/**
 * Created by akanshugupta9 on 13/1/17.
 */

public class FirstFragment extends Fragment {

    private static final String TAG = "FirstFragment";
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.first_frag, container, false);

        updateData();

        return v;
    }

    public void updateData(){
        DBHelper dbHelper = new DBHelper(getContext());
        float spendable = dbHelper.getSpendable();
        float nonSpendable = dbHelper.getNonSpendable();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String tmp = prefs.getString("denomination_preference", "₹");
        TextView tv = (TextView)v.findViewById(R.id.spendable_amount);
        tv.setText(Float.toString(spendable)+tmp);
        tv = (TextView)v.findViewById(R.id.non_spendable_amount);
        tv.setText(Float.toString(nonSpendable)+tmp);
        tv = (TextView)v.findViewById(R.id.total_amount);
        tv.setText(Float.toString(spendable+nonSpendable)+tmp);
    }

    public static FirstFragment newInstance(String text) {

        FirstFragment f = new FirstFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}