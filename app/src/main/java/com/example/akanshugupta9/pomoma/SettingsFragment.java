package com.example.akanshugupta9.pomoma;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * Created by akanshugupta9 on 13/1/17.
 */

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        for(int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
            initializeSummary(getPreferenceScreen().getPreference(i));
        }
    }

    private void initializeSummary(Preference p)
    {
        if(p instanceof ListPreference) {
            ListPreference listPref = (ListPreference)p;
            p.setSummary(listPref.getEntry());
        }
        if(p instanceof EditTextPreference) {
            EditTextPreference editTextPref = (EditTextPreference)p;
            p.setSummary(editTextPref.getText());
        }
    }

    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference pref = findPreference(key);
        if (pref instanceof EditTextPreference) {
            EditTextPreference etp = (EditTextPreference) pref;
            pref.setSummary(etp.getText());
        }
        if (pref instanceof ListPreference) {
            ListPreference etp = (ListPreference) pref;
            pref.setSummary(etp.getEntry());
        }
    }
}
