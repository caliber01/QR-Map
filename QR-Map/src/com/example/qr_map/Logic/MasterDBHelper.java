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



public class MasterDBHelper extends SQLiteOpenHelper {

	final String LOG_TAG = "MasterDBHelper";
	public SQLiteDatabase myDataBase; 
	private Context myContext;
	String version;
	public UpDataAccess up;  
	
    public MasterDBHelper(Context context,String DBName) {//path
      super(context,DBName, null, 1);
      myContext = context;
      myDataBase = getWritableDatabase();
      setVersionInMaster();
      up = new UpDataAccess(myContext,"1");//Integer.valueOf(
      
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
      db.execSQL("create table Version ("
    		  + "Version text primary key" + ");");
      db.execSQL("insert into Laboratory(Number, Name,Type , PhoneNumber , Activity ,AverageRating ,ChiefFIO ,LabAssistantsFIOs ,WorkTime ,SponsorName ,Faculty ,Cathedra ) values ('339','Sigma Lab','Computer class','123 456','Computing','6.23','Henry Smitt','bra,bro,bru','10.00-20.00','Sigma','KN','PI');");
      db.execSQL("insert into Laboratory(Number, Name,Type , PhoneNumber , Activity ,AverageRating ,ChiefFIO ,LabAssistantsFIOs ,WorkTime ,SponsorName ,Faculty ,Cathedra ) values ('340','Fake Sigma Lab','Computer class','123 456','Computing','6.23','Henry Smitt','bra,bro,bru','10.00-20.00','Sigma','KN','PI');");
      db.execSQL("insert into Sponsor(Name,WebSite , Address ,Telephone  ,Description ) values ('Sigma','www.sigma.com','aaddrreess','3456','cool company');");
      db.execSQL("insert into LabEquipment(Number,Electronic,HasProjector,HasWiFi,WiFiName,Tables,Chairs )  values ('339','electronic','1','1','339','12','18');");
      db.execSQL("insert into Version (Version) values('1')");
   }

    
   
   public void setVersionInMaster()
   {
	   query_one("Version",null,null,null,null,null,null);

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
   
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    	/*try{
    		ArrayList<String> tempString = insertIntoLaboratory();
		      if (Integer.valueOf(oldVersion) < Integer.valueOf(newVersion)) 
		      {
		    	  for(int i = 0; i < tempString.size();i++)
		    	  {
		    		  db.execSQL(tempString.get(i));
		    	  }
		      }
    	}
    	catch(Exception e)
    	{
    		
    	}*/
		       
}


   
   private ArrayList<String> insertIntoLaboratory()throws Exception
   {
	   String values = "insert into Laboratory(Number, Name,Type , PhoneNumber , Activity ,AverageRating ,ChiefFIO ,LabAssistantsFIOs ,WorkTime ,SponsorName ,Faculty ,Cathedra )  values (";
	   String[] temp = up.Getlab();
	   String val = values;
	   ArrayList<String> tempString = new ArrayList<String>();
	   int a = 0;
	   for(int i = 0;i < temp.length;i++)
	   {
		   if (temp[i] != "---")
	   		val += "'" + temp[i] + "'" + ",";
		   else
			   tempString.add(a,val);
			   val = values; 
	   }
		   
	   return tempString;
   }
   
   /*private String insertIntoSponsor()throws Exception
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
   }*/
    
    public String getVersion()
    {
    	return Integer.valueOf(version).toString();
    }

  
	
  }
	

