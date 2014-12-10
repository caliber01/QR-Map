package com.example.qr_map.Adapters;

import java.util.List;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qr_map.R;
import com.example.qr_map.Models.Laboratory;
import com.example.qr_map.Models.Room;

public class LabListAdapter extends RecyclerView.Adapter<LabListViewHolder> 
{
	private List<Room> labList;
	private Fragment fragment;
	
	public LabListAdapter(List<Room> _labList, Fragment _fragment)
	{
		labList = _labList;
		fragment = _fragment;
	}
	
	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return labList.size();
	}

	@Override
	public void onBindViewHolder(LabListViewHolder labViewHolder, int position) {
		// TODO Auto-generated method stub
		Laboratory lab = (Laboratory)labList.get(position);
		labViewHolder.vTitle.setText(lab.getNumber());
		labViewHolder.vInfo.setText(lab.getName());
		labViewHolder.vSponsor.setText(lab.getSponsor().toString());
	}

	@Override
	public LabListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		// TODO Auto-generated method stub
		View itemView = LayoutInflater.from(viewGroup.getContext())
				.inflate(R.layout.lab_card, viewGroup,false);
		return new LabListViewHolder(itemView, fragment);
	}
	
}
