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
	private Context myContext;
	private String version;
	private UpDataAccess up = new UpDataAccess(myContext,version);
	
    public MasterDBHelper(Context context,String DBName) {//path
      super(context,DBName, null, 1);
      myContext = context;
      myDataBase = getWritableDatabase();
      //myDataBase = getReadableDatabase();
    }

   @Override
    public void onCreate(SQLiteDatabase db) {
      Log.d(LOG_TAG, "--- onCreate database ---");
      db.execSQL("create table Laboratory ("
          + "Number text primary key ," 
          + "Name text,"
          + "Type text," 
          + "PhoneNumber text," 
          + "Activity text,"
          + "AverageRating text,"
          + "ChiefFIO text,"
          + "LabAssistantsFIOs text,"
          + "WorkTime text,"
          + "SponsorName text,"
          + "Faculty text,"
          + "Cathedra text"
          + ");");
      db.execSQL("create table Sponsor ("
              + "Name text primary key ," 
              + "WebSite text,"
              + "Address text," 
              + "Telephone text," 
              + "Description text"
              + ");");
      db.execSQL("create table LabEquipment ("
    		  + "Number text primary key ," 
              + "Electronic text,"
              + "HasProjector text," 
              + "HasWiFi text," 
              + "WiFiName text,"
              + "Tables double,"
              + "Chairs text"
              + ");");
      db.execSQL("insert into Laboratory(Number, Name,Type , PhoneNumber , Activity ,AverageRating ,ChiefFIO ,LabAssistantsFIOs ,WorkTime ,SponsorName ,Faculty ,Cathedra ) values ('339','Sigma Lab','Computer class','123 456','Computing','6.23','Henry Smitt','bra,bro,bru','10.00-20.00','Sigma','KN','PI');");
      db.execSQL("insert into Sponsor(Name,WebSite , Address ,Telephone  ,Description ) values ('Sigma','www.sigma.com','aaddrreess','3456','cool company');");
      db.execSQL("insert into Equipment(Number,Electronic,HasProjector,HasWiFi,WiFiName,Tables,Chairs )  values ('339','electronic','1','1','12','18');");
   }
    
   private String insertInto(String val,String[] temp)throws Exception
   {
	   String values = val;
	   for(int i = 0;i < temp.length;i++)
	   {
		   if(i == temp.length)
			   values += "'" + temp[i] + "'";
		   else
			   values += "'" + temp[i] + "'" + ",";
	   }
	   values += ");";
	   
	   return values;
   }
   
   private String insertIntoLaboratory()throws Exception
   {
	   String values = "insert into Laboratory(Number, Name,Type , PhoneNumber , Activity ,AverageRating ,ChiefFIO ,LabAssistantsFIOs ,WorkTime ,SponsorName ,Faculty ,Cathedra )  values (";
	   String[] temp = up.Getlab();
	   return insertInto(values,temp);
   }
   
   private String insertIntoSponsor()throws Exception
   {
	   String values = "insert into Sponsor(Name,WebSite , Address ,Telephone  ,Description ) values (";
	   String[] temp = up.GetSponsor();
	   return insertInto(values,temp);
   }
   
   private String insertIntoEquipment()throws Exception
   {
	   String values = "insert into Equipment(Number,Electronic,HasProjector,HasWiFi,WiFiName,Tables,Chairs )  values (";
	   String[] temp = up.GetEquipment();
	   return insertInto(values,temp);
   }
    
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Hashtable<String,String> query_one(String table,String[] columns,String selection,String[] selectionArgs,String groupBy,String having,String orderBy)
    {
    	Hashtable<String,String> h = new Hashtable<String,String>();
    	Cursor c = myDataBase.query(table, columns, selection,selectionArgs, groupBy, having, orderBy);
    	if (c != null)
		 {
			 if (c.moveToFirst()) {
				 for (String cn : c.getColumnNames()) {
					 if(c.getString(c.getColumnIndex(cn)) != null)
					 	{
						 h.put(cn, c.getString(c.getColumnIndex(cn)));
					 	}
			          }
			 }
		 }
    	return h;
    }
    public List<Hashtable<String,String>> query_many(String table,String[] columns,String selection,String[] selectionArgs,String groupBy,String having,String orderBy)
    {
    	List<Hashtable<String,String>> listH = new ArrayList<Hashtable<String,String>>();
    	Cursor c = myDataBase.query(table, columns, selection,selectionArgs, groupBy, having, orderBy);
    	if (c != null)
		 {
			 if (c.moveToFirst()) {
				 do {
					 Hashtable<String,String> h = new Hashtable<String,String>();
					 for (String cn : c.getColumnNames()) {
						 if(c.getString(c.getColumnIndex(cn)) != null)
						 	{
							 	h.put(cn, c.getString(c.getColumnIndex(cn)));
						 	}
			          	}
					 listH.add(h);
				 } while (c.moveToNext());
			 }
		 }
    	return listH;
    }
    
    private String getVersion()
    {
    	return "1";//metadata
    }

	
  }


