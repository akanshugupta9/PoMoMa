package com.example.akanshugupta9.pomoma;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.FloatProperty;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Date;

import static android.support.v7.widget.AppCompatDrawableManager.get;
import static com.example.akanshugupta9.pomoma.R.id.rl;

/**
 * Created by akanshugupta9 on 13/1/17.
 */

public class SecondFragment extends Fragment {

    private Button spendButton;
    private static final String TAG = "FirstFragment";
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.second_frag, container, false);

        spendButton = (Button)v.findViewById(R.id.spend_button);
        spendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText amountEt = (EditText) v.findViewById(R.id.amount_et);
                EditText summaryEt = (EditText) v.findViewById(R.id.reason);
                if (amountEt.getText().toString().trim().equals("")) {
                    Snackbar.make(v, "Looks like you haven't spent any amount...", Snackbar.LENGTH_LONG).show();
                } else if (summaryEt.getText().toString().trim().equals("")) {
                    Snackbar.make(v, "C'mon! There should be some reason for this purchase.", Snackbar.LENGTH_LONG).show();
                } else {
                    DBHelper dbHelper = new DBHelper(getContext());
                    Float amount = Float.valueOf(amountEt.getText().toString());
                    ToggleButton deductFromTb = (ToggleButton) v.findViewById(R.id.deduct_from);
                    Integer deductFrom;
                    if (deductFromTb.isChecked()) deductFrom = 1;
                    else deductFrom = 0;
                    if (dbHelper.insertTransaction(-amount, deductFrom, summaryEt.getText().toString(), String.valueOf(new Date()))) {
                        amountEt.setText("");
                        summaryEt.setText("");
                        Snackbar.make(v, "Data has been added. Go spend more.", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });

        return v;
    }

    public static SecondFragment newInstance(String text) {

        SecondFragment f = new SecondFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}