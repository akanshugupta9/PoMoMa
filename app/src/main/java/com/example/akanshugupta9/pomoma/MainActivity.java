package com.example.akanshugupta9.pomoma;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout mRelativeLayout;

    private PopupWindow mPopupWindow;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);

        mRelativeLayout = (CoordinatorLayout) findViewById(R.id.rl);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize a new instance of LayoutInflater service
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

                // Inflate the custom layout/view
                final View customView = inflater.inflate(R.layout.popup_form,null);

                /*
                    public PopupWindow (View contentView, int width, int height)
                        Create a new non focusable popup window which can display the contentView.
                        The dimension of the window must be passed to this constructor.

                        The popup does not provide any background. This should be handled by
                        the content view.

                    Parameters
                        contentView : the popup's content
                        width : the popup's width
                        height : the popup's height
                */
                // Initialize a new instance of popup window
                mPopupWindow = new PopupWindow(
                        customView,
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );

                mPopupWindow.setFocusable(true);
                mPopupWindow.update();

                // Set an elevation value for popup window
                // Call requires API level 21
                if(Build.VERSION.SDK_INT>=21){
                    mPopupWindow.setElevation(5.0f);
                }

                // Get a reference for the custom view close button
                ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

                // Set a click listener for the popup window close button
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });

                Button button = (Button)customView.findViewById(R.id.submit);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToggleButton sendRecieve = (ToggleButton)customView.findViewById(R.id.send_recieve);
                        if(sendRecieve.isChecked()){
                            //if on recieve
                            EditText amountEt = (EditText)customView.findViewById(R.id.amount);
                            EditText summaryEt = (EditText)customView.findViewById(R.id.summary);
                            if(amountEt.getText().toString().trim().equals("")){
                                Snackbar.make(mRelativeLayout, "What! You didn't receive any amount.", Snackbar.LENGTH_LONG).show();
                            }else if(summaryEt.getText().toString().trim().equals("")){
                                Snackbar.make(mRelativeLayout, "C'mon! Tell me, who is this secret sender?", Snackbar.LENGTH_LONG).show();
                            }else {
                                DBHelper dbHelper = new DBHelper(getApplicationContext());
                                Float amount = Float.valueOf(amountEt.getText().toString());
                                ToggleButton deductFromTb = (ToggleButton) customView.findViewById(R.id.spendable_non_spendable);
                                Integer deductFrom;
                                if (deductFromTb.isChecked()) deductFrom = 1;
                                else deductFrom = 0;
                                if (dbHelper.insertTransaction(amount, deductFrom, summaryEt.getText().toString(), String.valueOf(new Date()))) {
                                    amountEt.setText("");
                                    summaryEt.setText("");
                                    Snackbar.make(mRelativeLayout, "Money has been added. Start spendig it now.", Snackbar.LENGTH_LONG).show();
                                    mPopupWindow.dismiss();
                                }
                            }
                        }else{
                            EditText amountEt = (EditText) customView.findViewById(R.id.amount);
                            EditText summaryEt = (EditText) customView.findViewById(R.id.summary);
                            if (amountEt.getText().toString().trim().equals("")) {
                                Snackbar.make(mRelativeLayout, "Looks like you haven't spent any amount...", Snackbar.LENGTH_LONG).show();
                            } else if (summaryEt.getText().toString().trim().equals("")) {
                                Snackbar.make(mRelativeLayout, "C'mon! There should be some reason for this purchase.", Snackbar.LENGTH_LONG).show();
                            } else {
                                DBHelper dbHelper = new DBHelper(getApplicationContext());
                                Float amount = Float.valueOf(amountEt.getText().toString());
                                ToggleButton deductFromTb = (ToggleButton) customView.findViewById(R.id.spendable_non_spendable);
                                Integer deductFrom;
                                if (deductFromTb.isChecked()) deductFrom = 1;
                                else deductFrom = 0;
                                if (dbHelper.insertTransaction(-amount, deductFrom, summaryEt.getText().toString(), String.valueOf(new Date()))) {
                                    amountEt.setText("");
                                    summaryEt.setText("");
                                    Snackbar.make(mRelativeLayout, "Data has been added. Go spend more.", Snackbar.LENGTH_LONG).show();
                                    mPopupWindow.dismiss();
                                }
                            }
                        }

                    }
                });

                /*
                    public void showAtLocation (View parent, int gravity, int x, int y)
                        Display the content view in a popup window at the specified location. If the
                        popup window cannot fit on screen, it will be clipped.
                        Learn WindowManager.LayoutParams for more information on how gravity and the x
                        and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
                        to specifying Gravity.LEFT | Gravity.TOP.

                    Parameters
                        parent : a parent view to get the getWindowToken() token from
                        gravity : the gravity which controls the placement of the popup window
                        x : the popup's x location offset
                        y : the popup's y location offset
                */
                // Finally, show the popup window at the center location of root relative layout
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.about:
                startActivity(new Intent(this, AboutActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
