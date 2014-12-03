package com.example.qr_map.Logic;
import java.util.List;
import java.util.*;

import com.example.qr_map.Models.Laboratory;
import com.example.qr_map.Models.Room;
import com.example.qr_map.Models.Sponsor;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.Context;
import android.util.Log;



public class MasterDBHelper extends SQLiteOpenHelper {

	final String LOG_TAG = "MasterDBHelper";
	private SQLiteDatabase myDataBase; 
	private final Context myContext;
	
    public MasterDBHelper(Context context,String DBName) {//path
      super(context,DBName, null, 1);
      myContext = context;
      myDataBase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      /*Log.d(LOG_TAG, "--- onCreate database ---");
      // создаем таблицу с полями
      db.execSQL("create table Laboratory ("
          + "Number text primary key ," 
          + "Name text,"
          + "Type text," 
          + "PhoneNumber text," 
          + "Activity text,"
          + "AverageRating double,"
          + "ChiefFIO text,"
          + "LabAssistantsFIOs text,"
          + "WorkTime text,"
          + "SponsorName text,"
          + "Faculty text,"
          + "Cathedra text"
          + ");");*/
    }

    
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Hashtable<String,String> query_one(String table,String[] columns,String selection,String[] selectionArgs,String groupBy,String having,String orderBy)
    {
    	Hashtable<String,String> h = null;
    	Cursor c = myDataBase.query(table, columns, selection,selectionArgs, groupBy, having, orderBy);
    	if (c != null)
		 {
			 if (c.moveToFirst()) {
				 for (String cn : c.getColumnNames()) {
			            h.put(cn, c.getString(c.getColumnIndex(cn)));
			          }
			 }
		 }
    	return h;
    }
    public List<Hashtable<String,String>> query_many(String table,String[] columns,String selection,String[] selectionArgs,String groupBy,String having,String orderBy)
    {
    	List<Hashtable<String,String>> listH = null;
    	Cursor c = myDataBase.query(table, columns, selection,selectionArgs, groupBy, having, orderBy);
    	if (c != null)
		 {
			 if (c.moveToFirst()) {
				 do {
					 Hashtable<String,String> h = new Hashtable<String,String>();
					 for (String cn : c.getColumnNames()) {
						 h.put(cn, c.getString(c.getColumnIndex(cn)));
			          	}
					 listH.add(h);
				 } while (c.moveToNext());
			 }
		 }
    	return listH;
    }
	

	public List<Room> FindByName(String _Name) {
		// TODO Auto-generated method stub
		return null;
	}


	public void AddToHistory(String roomId) {
		// TODO Auto-generated method stub

	}


	public List<Room> GetHistory() {
		// TODO Auto-generated method stub
		return null;
	}


	public void AddToFavourites(String roomNumber) {
		// TODO Auto-generated method stub

	}


	public void RemoveFromFavourites(String roomNumber) {
		// TODO Auto-generated method stub

	}


	public void UpdateRating(String roomNumber, int rating) {
		// TODO Auto-generated method stub

	}


	public List<Room> GetFavourites() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Laboratory> FindBySponsor(Sponsor _Sponsor) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Room> FindByNumber(String _Number) {
		// TODO Auto-generated method stub
		return null;
	}
  }


