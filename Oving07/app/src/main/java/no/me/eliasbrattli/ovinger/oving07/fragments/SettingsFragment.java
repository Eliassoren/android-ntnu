package no.me.eliasbrattli.ovinger.oving07.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import no.me.eliasbrattli.ovinger.oving07.R;

/**
 * Created by mildrid on 07.10.2015.
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}