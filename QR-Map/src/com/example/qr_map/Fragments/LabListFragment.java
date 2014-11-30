package com.example.qr_map.Fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.qr_map.R;
import com.example.qr_map.Logic.ForMaxTestLabDataAccess;
import com.example.qr_map.Models.Room;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LabListFragment extends Fragment {

	private final String MENU_ITEM_POSITION = "position";
	private String MY_LOG ="log";
	
	private final String ROOM_NUMBER_ATTRIBUTE = "number";
	private final String ROOM_INFO_ATTRIBUTE = "info";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		//забираем позицию
		int position = this.getArguments().getInt(MENU_ITEM_POSITION);
		
		
		View frag = inflater.inflate(R.layout.lab_list_fragment, null);
		ListView fListView = (ListView) frag.findViewById(R.id.lv_lab_list);
		
		ForMaxTestLabDataAccess data = new ForMaxTestLabDataAccess();
		List<Room> rooms = null;
    	switch(position)
    	{
    		case 0:
    			rooms = data.GetAll();
		        break;
    		case 1:
    			rooms = data.GetAll();
		        break;
    		case 2:
    			rooms = data.GetAll();
		        break;
        }
    	ArrayList<HashMap<String,String>> content = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> m;
		for(Room r : rooms)
		{	
			//Log.i(MY_LOG, r.getNumber());
			m = new HashMap<String,String>();
			m.put(ROOM_NUMBER_ATTRIBUTE,"301b");
			m.put(ROOM_INFO_ATTRIBUTE, r.toString());
			content.add(m);
		}
		String[] from = {ROOM_NUMBER_ATTRIBUTE,ROOM_INFO_ATTRIBUTE};
		int[] to = {R.id.lab_list_item_number,R.id.lab_list_item_info};
		SimpleAdapter adapter = new SimpleAdapter(getActivity(),content,R.layout.lab_list_item, from, to);
		fListView.setAdapter(adapter);
		return frag;
	}
}
