package com.example.qr_map.Adapters;

import android.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.example.qr_map.R;
import com.example.qr_map.Activities.MainActivity;
import com.example.qr_map.Adapters.LabInfoViewHolder;

public class LabListViewHolder extends LabInfoViewHolder
{
	protected TextView vSponsor;
	
	public LabListViewHolder(View v)
	{
		super(v);
		vTitle =  (TextView) v.findViewById(R.id.lab_number);
		vInfo = (TextView) v.findViewById(R.id.lab_name);
		vSponsor = (TextView) v.findViewById(R.id.lab_sponsor);
	}
	public LabListViewHolder(View v, Fragment f)
	{
		super(v);
		vTitle =  (TextView) v.findViewById(R.id.lab_number);
		vInfo = (TextView) v.findViewById(R.id.lab_name);
		vSponsor = (TextView) v.findViewById(R.id.lab_sponsor);
		v.setOnClickListener((MainActivity)f.getActivity());
	}
}
