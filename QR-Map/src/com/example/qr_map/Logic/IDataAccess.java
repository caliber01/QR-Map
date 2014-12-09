package com.example.qr_map.Logic;

import java.util.List;

import com.example.qr_map.Models.Laboratory;
import com.example.qr_map.Models.Room;


public interface IDataAccess {
	//Get the room by id (its number)
	public Laboratory GetRoom(String _Number);
	
	public List<Room> GetAll();
	
	//Force request update database from the server when the app gets internet
	public void UpdateInfoFromServer();
	
	public List<Room> FindByNumber(String _Number);//gggggggg
	
	public List<Room> FindByName(String _Name);
	
	public void AddToHistory(String roomId);
	
	public List<Room> GetHistory();
	
	public void AddToFavourites(String roomNumber);
	
	public void RemoveFromFavourites(String roomNumber);
	
	public void UpdateRating(String roomNumber, int rating);
	
	public List<Room> GetFavourites();
	
	public int CountAll();
	
	public int CountFavourites();
	
	public int CountHistory();
}
