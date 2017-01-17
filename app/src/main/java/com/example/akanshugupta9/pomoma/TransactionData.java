package com.example.akanshugupta9.pomoma;

import java.util.ArrayList;
import java.util.Date;

import static com.example.akanshugupta9.pomoma.R.id.date;

/**
 * Created by akanshugupta9 on 17/1/17.
 */

public class TransactionData {
    ArrayList<String> summary;
    ArrayList<Integer> type;
    ArrayList<Float> amount;
    ArrayList<String> date;

    public TransactionData(){
        summary = new ArrayList<String>();
        type = new ArrayList<Integer>();
        amount = new ArrayList<Float>();
        date = new ArrayList<String>();
    }
}
