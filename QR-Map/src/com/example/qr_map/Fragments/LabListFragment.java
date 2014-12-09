package com.example.qr_map.Fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qr_map.R;
import com.example.qr_map.Logic.DataAccess;
import com.example.qr_map.Models.Laboratory;
import com.example.qr_map.Models.Room;
import com.melnykov.fab.FloatingActionButton;

public class LabListFragment extends Fragment {

	private DataAccess mDataAccess;
	
	private final String MENU_ITEM_POSITION = "position";
	private final String ROOM_NUMBER_ATTRIBUTE = "number";
	private final String ROOM_INFO_ATTRIBUTE = "info";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		//забираем позицию
		int position = this.getArguments().getInt(MENU_ITEM_POSITION);
		
		
		View frag = inflater.inflate(R.layout.lab_cards_fragment, null);
		RecyclerView recView = (RecyclerView) frag.findViewById(R.id.cardList);
		recView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recView.setLayoutManager(llm);
        
        FloatingActionButton fab = (FloatingActionButton) frag.findViewById(R.id.fab);
        fab.attachToRecyclerView(recView);
		
		
		mDataAccess = new DataAccess(this.getActivity());
		LabAdapter adapter = null;
		switch(position){
		case 1:
			adapter = new LabAdapter(mDataAccess.GetAll());
			break;
		case 2:
			adapter = new LabAdapter(mDataAccess.GetFavourites());
			break;
		case 3:
			adapter = new LabAdapter(mDataAccess.GetHistory());
			 break;
		}
		recView.setAdapter(adapter);
		
		return frag;
	}
	public void someMethod(){}
	public class LabAdapter extends RecyclerView.Adapter<LabViewHolder> 
	{
		private List<Room> labList;
		
		public LabAdapter(List<Room> labList)
		{
			this.labList = labList;
		}
		
		@Override
		public int getItemCount() {
			// TODO Auto-generated method stub
			return labList.size();
		}

		@Override
		public void onBindViewHolder(LabViewHolder labViewHolder, int position) {
			// TODO Auto-generated method stub
			Laboratory lab = (Laboratory)labList.get(position);
			labViewHolder.vName.setText(lab.getName());
			labViewHolder.vNumber.setText("310b");//lab.getNumber());
			labViewHolder.vSponsor.setText(lab.getSponsor().toString());
		}

		@Override
		public LabViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
			// TODO Auto-generated method stub
			View itemView = LayoutInflater.from(viewGroup.getContext())
					.inflate(R.layout.lab_card, viewGroup,false);
			return new LabViewHolder(itemView);
		}
		
	}
	
	public class LabViewHolder extends RecyclerView.ViewHolder
	{
		protected TextView vNumber;
		protected TextView vName;
		protected TextView vSponsor;
		public LabViewHolder(View v)
		{
			super(v);
			vNumber =  (TextView) v.findViewById(R.id.lab_number);
			vName = (TextView) v.findViewById(R.id.lab_name);
			vSponsor = (TextView) v.findViewById(R.id.lab_sponsor);
		}
	}
}
