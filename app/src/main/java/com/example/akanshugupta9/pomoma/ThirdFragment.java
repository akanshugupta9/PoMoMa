package com.example.akanshugupta9.pomoma;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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

import static android.content.ContentValues.TAG;

/**
 * Created by akanshugupta9 on 13/1/17.
 */

public class ThirdFragment extends Fragment {

    Button recieveButton;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.third_frag, container, false);

        recieveButton = (Button)v.findViewById(R.id.recieve_button);
        recieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText amountEt = (EditText)v.findViewById(R.id.amount_recieved);
                EditText summaryEt = (EditText)v.findViewById(R.id.recieved_from);
                if(amountEt.getText().toString().trim().equals("")){
                    Snackbar.make(v, "What! You didn't receive any amount.", Snackbar.LENGTH_LONG).show();
                }else if(summaryEt.getText().toString().trim().equals("")){
                    Snackbar.make(v, "C'mon! Tell me, who is this secret sender?", Snackbar.LENGTH_LONG).show();
                }else {
                    DBHelper dbHelper = new DBHelper(getContext());
                    Float amount = Float.valueOf(amountEt.getText().toString());
                    ToggleButton deductFromTb = (ToggleButton) v.findViewById(R.id.add_to);
                    Integer deductFrom;
                    if (deductFromTb.isChecked()) deductFrom = 1;
                    else deductFrom = 0;
                    if (dbHelper.insertTransaction(amount, deductFrom, summaryEt.getText().toString(), String.valueOf(new Date()))) {
                        amountEt.setText("");
                        summaryEt.setText("");
                        Snackbar.make(v, "Money has been added. Start spendig it now.", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });

        return v;
    }

    public static ThirdFragment newInstance(String text) {

        ThirdFragment f = new ThirdFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}