package com.example.qr_map.Logic;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import android.content.Context;

import com.example.qr_map.Models.Laboratory;
import com.example.qr_map.Models.Room;
import com.example.qr_map.Models.Sponsor;

@SuppressWarnings("unused")
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
	
	String filenameHistory = "/History.os";
	String filenameFavourites = "/Favourites.os";
	
	
	@Override
	public void AddToHistory(String roomId) {
		try{
		LocalHelper.add(filenameHistory,roomId);
		}
		catch(Exception e)
		{
			
		}
		
	}
	@Override
	public List<Room> GetHistory() {
		ArrayList listLabs = new ArrayList();
		Stack<String> st = LocalHelper.read(filenameHistory);
		while(st.empty())
		{
			String s = st.pop();
			Laboratory lab = new Laboratory();
			lab = GetRoom(s);
			if(lab != null)
			{
				listLabs.add(lab);
			}
		}
		return listLabs;
	}

	@Override
	public void AddToFavourites(String roomNumber) {
		try{
			LocalHelper.addToQueue(filenameFavourites,roomNumber);
			}
			catch(Exception e)
			{
				
			}

	}

	@Override
	public void RemoveFromFavourites(String roomNumber) {
		try{
			LocalHelper.removeFromQueue(filenameFavourites,roomNumber);
			}
			catch(Exception e)
			{
				
			}

	}
	@Override
	public List<Room> GetFavourites() {
		ArrayList listLabs = new ArrayList();
		PriorityQueue q = LocalHelper.readQueue(filenameFavourites);
		while(q.isEmpty())
		{
			String s = (String)q.poll();
			Laboratory lab = new Laboratory();
			lab = GetRoom(s);
			if(lab != null)
			{
				listLabs.add(lab);
			}
		}
		return listLabs;
	}

	@Override
	public void UpdateRating(String roomNumber, int rating) {
		// TODO Auto-generated method stub

	}
	@Override
	public void UpdateInfoFromServer() {
		// TODO Auto-generated method stub

	}
	
	

	
	
}
