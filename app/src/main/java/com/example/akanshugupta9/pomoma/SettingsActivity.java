package com.example.akanshugupta9.pomoma;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by akanshugupta9 on 13/1/17.
 */

public class SettingsActivity extends Activity {

    SampleAlarmReceiver alarm = new SampleAlarmReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        alarm.cancelAlarm(this);
        alarm.setAlarm(this);
        super.onBackPressed();
    }
}
