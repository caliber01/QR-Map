package com.example.qr_map.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qr_map.R;

public class LabTabFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View frag = inflater.inflate(R.layout.lab_cards_fragment, null);
		return frag;
	}
}
