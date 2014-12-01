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
import com.example.qr_map.Logic.ForMaxTestLabDataAccess;
import com.example.qr_map.Models.Laboratory;
import com.example.qr_map.Models.Room;

public class LabListFragment extends Fragment {

	private final String MENU_ITEM_POSITION = "position";
	
	private final String ROOM_NUMBER_ATTRIBUTE = "number";
	private final String ROOM_INFO_ATTRIBUTE = "info";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		//�������� �������
		int position = this.getArguments().getInt(MENU_ITEM_POSITION);
		
		
		View frag = inflater.inflate(R.layout.lab_cards_fragment, null);
		RecyclerView recView = (RecyclerView) frag.findViewById(R.id.cardList);
		recView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recView.setLayoutManager(llm);
		
		
		ForMaxTestLabDataAccess data = new ForMaxTestLabDataAccess();
		LabAdapter adapter = new LabAdapter(data.GetAll());
		recView.setAdapter(adapter);
		return frag;
	}
	
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
			labViewHolder.vInfo.setText(lab.toString());
			labViewHolder.vNumber.setText("310b");//lab.getNumber());
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
		protected TextView vInfo;
		
		public LabViewHolder(View v)
		{
			super(v);
			vNumber =  (TextView) v.findViewById(R.id.lab_number);
			vInfo = (TextView) v.findViewById(R.id.lab_info);
		}
	}
}
