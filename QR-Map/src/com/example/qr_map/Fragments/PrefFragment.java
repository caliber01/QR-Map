package com.example.qr_map.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;

import com.example.qr_map.R;


	public class PrefFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {
		@Override
		  public void onCreate(Bundle savedInstanceState) {
			    super.onCreate(savedInstanceState);
			 // Make sure default values are applied.  In a real app, you would
	            // want this in a shared function that is used to retrieve the
	            // SharedPreferences wherever they are needed.
	            //PreferenceManager.setDefaultValues(getActivity(),
	               //     R.xml.advanced_preferences, false);
			    addPreferencesFromResource(R.layout.pref_fragment);
		    }
		@Override
		public void onResume() {
		    super.onResume();
		    // Set up a listener whenever a key changes
		    getPreferenceScreen().getSharedPreferences()
		            .registerOnSharedPreferenceChangeListener(this);
		}

		@Override
		public void onPause() {
		    super.onPause();
		    // Unregister the listener whenever a key changes
		    getPreferenceScreen().getSharedPreferences()
		            .unregisterOnSharedPreferenceChangeListener(this);
		}

		public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,String key) 
		{
			if(key.equals("theme"))
			{
				Log.i("mylog",key);
				Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(getActivity().getPackageName());
				getActivity().finish();
				startActivity(intent);
			}
		}
	}

