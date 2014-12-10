package com.example.qr_map.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qr_map.R;
import com.example.qr_map.Activities.MainActivity;
import com.example.qr_map.Adapters.LabListAdapter;
import com.example.qr_map.Logic.DataAccess;
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
		fab.setOnClickListener((MainActivity)this.getActivity());
		
		mDataAccess = new DataAccess(this.getActivity(),"qr.db");
		LabListAdapter adapter = null;
		switch(position){
		case 1:
			adapter = new LabListAdapter(mDataAccess.GetAll());
			break;
		case 2:
			adapter = new LabListAdapter(mDataAccess.GetFavourites());
			break;
		case 3:
			adapter = new LabListAdapter(mDataAccess.GetHistory());
			 break;
		}
		recView.setAdapter(adapter);
		
		return frag;
	}
	
	
	
	
}
