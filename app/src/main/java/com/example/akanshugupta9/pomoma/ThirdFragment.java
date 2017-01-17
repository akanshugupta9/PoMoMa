package com.example.akanshugupta9.pomoma;

import android.os.Bundle;
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
                DBHelper dbHelper = new DBHelper(getContext());
                EditText amountEt = (EditText)v.findViewById(R.id.amount_recieved);
                Float amount = Float.valueOf(amountEt.getText().toString());
                ToggleButton deductFromTb = (ToggleButton)v.findViewById(R.id.add_to);
                Integer deductFrom;
                if (deductFromTb.isChecked()) deductFrom = 1;
                else deductFrom = 0;
                EditText summaryEt = (EditText)v.findViewById(R.id.recieved_from);
                if(dbHelper.insertTransaction(amount, deductFrom, summaryEt.getText().toString(), String.valueOf(new Date()))){
                    Toast.makeText(getContext(), "Data Updated...", Toast.LENGTH_SHORT);
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