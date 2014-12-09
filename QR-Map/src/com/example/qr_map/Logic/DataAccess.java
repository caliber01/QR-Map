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
import com.example.qr_map.Models.LabEquipment;

@SuppressWarnings("unused")
public class DataAccess implements ILabDataAccess
{
	private ArrayList<Room> labs;
	private MasterDBHelper masterDB;
	private Context conti;
	private String DbPath = "/data/data/com.example.qr_map/databases/";
	
	public DataAccess()
	{
		//con
	}
	
	public DataAccess(Context context)
	{
		this(context,null);
	}
	

	public DataAccess(Context context,String masterDBName)
	{
		conti = context;
		masterDB = null;
		setName(masterDBName);
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
		}
			masterDB = new MasterDBHelper(conti,DbPath + masterDBName);
		
	}
	
	
	private Laboratory setLab(Hashtable<String,String> h)
	{
		Laboratory l = new Laboratory();
		l.setName(h.get("Name"));
	     l.setType(h.get("Type"));
	     l.setPhoneNumber(h.get("PhoneNumber"));
	     l.setActivity(h.get("Activity"));
	     l.setAverageRating(Double.parseDouble(h.get("AverageRating")));
	     l.setChiefFIO(h.get("ChiefFIO"));
	     l.setLabAssistantsFIOs( labAssistantsFromString(h.get("LabAssistantsFIOs")));
	     l.setSponsor(gettingSponsor((h.get("SponsorName"))));
	     l.setFaculty(h.get("Faculty"));
	     l.setCathedra(h.get("Cathedra"));
	     return l;
	}
	
	private List<String> labAssistantsFromString(String labAssistantsFIOs)
	{
		List<String> LabAssistantsFIOs = new ArrayList<String>();
		String s[]= labAssistantsFIOs.split(",");
		for(int i = 0;i < s.length;i++)
		{
			LabAssistantsFIOs.add(s[i]);
		}
		return LabAssistantsFIOs;
	}
	
	private Sponsor gettingSponsor(String _SponsorName)
	{
		Sponsor sp = new Sponsor();
		String selection = "Name = ?";
	    String[] selectionArgs = new String[] { _SponsorName };
	    Hashtable<String,String> h = masterDB.query_one("Sponsor", null, selection, selectionArgs, null, null, null);
		if(h != null)
		{
			sp.setName(h.get("Name"));
			sp.setWebSite(h.get("WebSite"));
	     	sp.setDescription(h.get("Description"));
	     	sp.setAddress(h.get("Adress"));
	     	sp.setTelephone(h.get("Telephone"));
	     
		}
		return sp;
	}
	
	private LabEquipment gettingLabEquipment(String _Number)
	{
		LabEquipment le = new LabEquipment();
		String selection = "Number = ?";
	    String[] selectionArgs = new String[] { _Number };
	    Hashtable<String,String> h = masterDB.query_one("LabEquipment", null, selection, selectionArgs, null, null, null);
		if(h != null)
		{
			le.setNumber(h.get("Number"));
			le.setElectronic(h.get("Electronic"));
			le.setHasProjector(Boolean.parseBoolean(h.get("HasProjector")));
			le.setHasWiFi(Boolean.parseBoolean(h.get("HasWiFi")));
			le.setWiFiName(h.get("WiFiName"));
			le.setTables(Integer.parseInt(h.get("Tables")));
			le.setChairs(Integer.parseInt(h.get("Chairs")));
	     
		}
		return le;
	}
	
	
	private ArrayList FindBySmth(String selection,String[] selectionArgs)
	{
		ArrayList listLabs = new ArrayList();
	    List<Hashtable<String,String>> listH = masterDB.query_many("Laboratory", null, selection, selectionArgs, null, null, null);
	    if(listH != null)
	    {
	    	for(int i = 0;i < listH.size();i++)
	    	{
	    		listLabs.add(setLab(listH.get(i)));
	    	}
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
	
	String filenameHistory = "History.os";
	String filenameFavourites = "Favourites.os";
	
	
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
		Stack<String> st = LocalHelper.read(DbPath + filenameHistory);
		if(!st.empty())
		{
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
		PriorityQueue q = LocalHelper.readQueue(DbPath + filenameFavourites);
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
	
	@Override
	public int CountAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int CountFavourites() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int CountHistory() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
