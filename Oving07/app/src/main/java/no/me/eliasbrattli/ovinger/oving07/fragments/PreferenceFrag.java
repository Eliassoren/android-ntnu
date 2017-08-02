package no.me.eliasbrattli.ovinger.oving07.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by EliasBrattli on 18/11/2016.
 */
public class PreferenceFrag extends android.preference.PreferenceFragment implements Preference.OnPreferenceClickListener{
    public PreferenceFrag(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){

    }
    @Override
    public boolean onPreferenceClick(Preference preference){
        return false;
    }

}
