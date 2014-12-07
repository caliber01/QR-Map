package com.example.qr_map.Logic;

import java.util.ArrayList;
import java.util.List;

import com.example.qr_map.Models.Laboratory;
import com.example.qr_map.Models.Room;
import com.example.qr_map.Models.Sponsor;

public class ForMaxTestLabDataAccess implements ILabDataAccess {

	private ArrayList<Room> labs;
	
	public ForMaxTestLabDataAccess()
	{
		labs = new ArrayList();
		for(int i = 0; i< 30; i++)
		{
			Laboratory lab = new Laboratory(
					"Физическая лабаратория",
					new Sponsor(),
					"132",
					"Computer class",
					"Computer sciences",
					"Programming",
					"Ololosh",
					new ArrayList<String>(),
					"12:00 - 22:00",
					"Physics",
					3.5
					);
			lab.getSponsor().setName("Global Logic");
			labs.add(lab);
		}
	}
	
	@Override
	public Laboratory GetRoom(String _Number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> GetAll() {
		// TODO Auto-generated method stub
		return labs;
	}

	@Override
	public void UpdateInfoFromServer() {
		// TODO Auto-generated method stub

	}


	@Override
	public List<Room> FindByName(String _Name) {
		// TODO Auto-generated method stub
		return labs;
	}

	@Override
	public void AddToHistory(String roomId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Room> GetHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void AddToFavourites(String roomNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void RemoveFromFavourites(String roomNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void UpdateRating(String roomNumber, int rating) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Room> GetFavourites() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Laboratory> FindBySponsor(Sponsor _Sponsor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> FindByNumber(String _Number) {
		// TODO Auto-generated method stub
		return null;
	}

}
