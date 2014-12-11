package com.example.qr_map.Adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qr_map.R;
import com.example.qr_map.Logic.Showing;
import com.example.qr_map.Models.LabEquipment;
import com.example.qr_map.Models.Laboratory;


public class LabInfoAdapter extends RecyclerView.Adapter<LabInfoViewHolder>  {
	private String[] titles;
	
	private Laboratory lab;
	private ArrayList<HashMap<String,String>> infoList;
	
	private final String TITLE_KEY = "title";
	private final String INFO_KEY = "info";
	
	public LabInfoAdapter(Laboratory _lab,int resArrayId, Context _context)
	{
		Log.i("mylog",_lab.toString());
		infoList = new ArrayList<HashMap<String,String>>();
		lab = _lab;
		String[] titles = _context.getResources().getStringArray(resArrayId);
		String[] infos  = null;
		Showing s = new Showing();
		HashMap<String,String> m;
		
		switch(resArrayId){
		case R.array.lab_info_titles:
			infos = s.getLabInfo(lab);
			break;
		case R.array.lab_sponsor_titles:
			infos = s.getSponsorInfo(lab.getSponsor());
			break;
		case R.array.lab_equipment_titles:
			infos = s.getEquipmentInfo((LabEquipment)lab.getEquipment());
			break;	
		}
		
		for(int i = 0; i < titles.length; i++){
			m = new HashMap<String,String>();
			Log.i("melog", m.toString());
			if(infos[i]!=null){
				m.put(INFO_KEY,infos[i]);
			}
			else{
				m.put(INFO_KEY,"Нет");
			}
			m.put(TITLE_KEY,titles[i]);
			infoList.add(m);
		}
	}
	
	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return infoList.size();
	}

	@Override
	public void onBindViewHolder(LabInfoViewHolder labViewHolder, int position) {
		// TODO Auto-generated method stub
		HashMap<String,String> m = infoList.get(position);
		Log.i("mylog",labViewHolder.toString());
		labViewHolder.vTitle.setText(m.get(TITLE_KEY));
		labViewHolder.vInfo.setText(m.get(INFO_KEY));
	}

	@Override
	public LabInfoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		// TODO Auto-generated method stub
		View itemView = LayoutInflater.from(viewGroup.getContext())
				.inflate(R.layout.lab_info_card, viewGroup,false);
		return new LabInfoViewHolder(itemView);
	}
}
