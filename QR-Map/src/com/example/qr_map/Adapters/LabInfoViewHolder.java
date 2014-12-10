package com.example.qr_map.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.qr_map.R;

public class LabInfoViewHolder extends RecyclerView.ViewHolder
{
	protected TextView vTitle;
	protected TextView vInfo;
	
	public LabInfoViewHolder(View v)
	{
		super(v);
		vTitle =  (TextView) v.findViewById(R.id.lab_number);
		vInfo = (TextView) v.findViewById(R.id.lab_name);
	}
}
