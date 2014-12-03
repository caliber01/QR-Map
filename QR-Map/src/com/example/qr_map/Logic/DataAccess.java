package com.example.qr_map.Logic;

import java.util.*;

import com.example.qr_map.Models.Laboratory;
import com.example.qr_map.Models.Room;
import com.example.qr_map.Models.Sponsor;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.Context;
import android.util.Log;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.example.qr_map.Models.Laboratory;
import com.example.qr_map.Models.Room;
import com.example.qr_map.Models.Sponsor;

public class DataAccess implements ILabDataAccess
{
	private ArrayList<Room> labs;
	private MasterDBHelper masterDB;
	private MasterDBHelper localDB;
	private Context conti;
	
	public DataAccess()
	{
		
	}
	
	public DataAccess(Context context)
	{
		this(context,null,null);
		
	}
	
	public DataAccess(Context context,String masterDBName)
	{
		this(context,masterDBName,null);
	}
	
	public DataAccess(Context context,String masterDBName,String localDBName)
	{
		conti = context;
		setName(masterDBName);
		setLocalName(localDBName);
	}
	
	public void setContext(Context context)
	{
		conti = context;
	}
	
	public void setName(String masterDBName)
	{
		if(masterDB != null)
		{
			masterDB.close();
			masterDB = new MasterDBHelper(conti,masterDBName);
		}
	}
	
	public void setLocalName(String localDBName)
	{
		if(localDB != null)
		{
			localDB.close();
			localDB = new MasterDBHelper(conti,localDBName);
		}
	}
	
	private Laboratory setLab(Hashtable<String,String> h)
	{
		Laboratory l = new Laboratory();
		l.setName(h.get("Name"));
	     l.setName(h.get("Type"));
	     l.setName(h.get("PhoneNumber"));
	     l.setName(h.get("Activity"));
	     l.setName(h.get("AverageRating"));
	     l.setName(h.get("ChiefFIO"));
	     l.setName(h.get("LabAssistantsFIOs"));
	     l.setName(h.get("SponsorName"));
	     l.setName(h.get("Faculty"));
	     l.setName(h.get("Cathedra"));
	     return l;
	}
	
	
	private ArrayList FindBySmth(String selection,String[] selectionArgs)
	{
		ArrayList listLabs = new ArrayList();
	    List<Hashtable<String,String>> listH = masterDB.query_many("Laboratory", null, selection, selectionArgs, null, null, null);
	    for(int i = 0;i < listH.size();i++)
	    {
	    	listLabs.add(setLab(listH.get(i)));
	    }
		
	    return listLabs;
	}
	
	@Override
	public Laboratory GetRoom(String _Number) {
		Laboratory l = null;
		String selection = "Number = ?";
	    String[] selectionArgs = new String[] { _Number };
	    Hashtable<String,String> h = masterDB.query_one("Laboratory", null, selection, selectionArgs, null, null, null);
		if(h != null)
		{
			l = setLab(h);
		}
	    return l;
	}

	@Override
	public List<Room> GetAll() {
		return FindBySmth(null,null);
	}

	@Override
	public List<Room> FindByName(String _Name) {
		return FindBySmth("Name = ?",new String[] {_Name });
	}

	@Override
	public List<Laboratory> FindBySponsor(Sponsor _Sponsor) {

		return FindBySmth("SponsorName = ?",new String[] {_Sponsor.getName() });
		
	}
	
	@Override
	public List<Room> FindByNumber(String _Number) {
		return FindBySmth("Number = ?",new String[] {_Number});
	}
	
	@Override
	public void AddToHistory(String roomId) {
		

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
	public void UpdateInfoFromServer() {
		// TODO Auto-generated method stub

	}
	
	

	
	
}
