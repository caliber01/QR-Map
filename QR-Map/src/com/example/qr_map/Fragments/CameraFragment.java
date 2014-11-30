package com.example.qr_map.Fragments;

import com.example.qr_map.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CameraFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View frag = inflater.inflate(R.layout.camera_fragment, null);
		return frag;
	}
}
