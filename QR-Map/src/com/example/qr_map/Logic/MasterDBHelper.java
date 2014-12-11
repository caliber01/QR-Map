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
	final int version = 1;
	
    public MasterDBHelper(Context context,String DBName) {//path
      super(context,DBName, null, 1);
      myContext = context;
      myDataBase = getWritableDatabase();
      
      //myDataBase = getReadableDatabase();
    }

   @Override
    public void onCreate(SQLiteDatabase db) {
      Log.d(LOG_TAG, "--- onCreate database ---");
     // Log.d(LOG_TAG, " --- Staff db v." + myDataBase.getVersion() + " --- ");
    //  writeStaff(myDataBase);
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
      //db.execSQL("insert into Laboratory(Number, Name,Type , PhoneNumber , Activity ,AverageRating ,ChiefFIO ,LabAssistantsFIOs ,WorkTime ,SponsorName ,Faculty ,Cathedra ) values ('339','Sigma Lab','Computer class','123 456','Computing','6.23','Henry Smitt','bra,bro,bru','10.00-20.00','Sigma','KN','PI');");
      //db.execSQL("insert into Laboratory(Number, Name,Type , PhoneNumber , Activity ,AverageRating ,ChiefFIO ,LabAssistantsFIOs ,WorkTime ,SponsorName ,Faculty ,Cathedra ) values ('340','Fake Sigma Lab','Computer class','123 456','Computing','6.23','Henry Smitt','bra,bro,bru','10.00-20.00','Sigma','KN','PI');");
      //db.execSQL("insert into Sponsor(Name,WebSite , Address ,Telephone  ,Description ) values ('Sigma','www.sigma.com','aaddrreess','3456','cool company');");
      //db.execSQL("insert into LabEquipment(Number,Electronic,HasProjector,HasWiFi,WiFiName,Tables,Chairs )  values ('339','electronic','1','1','339','12','18');");
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
   
   @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	
	   /*Log.d(LOG_TAG, " --- onUpgrade database from " + oldVersion
		          + " to " + newVersion + " version --- ");

		      if (oldVersion == 1 && newVersion == 2) {*/
	   

		       

    }

    
    public String getVersion()
    {
    	return Integer.valueOf(version).toString();
    }

   /* private void writeStaff(SQLiteDatabase db) {
        Cursor c = db.rawQuery("select * from Laboratory", null);
        logCursor(c, "Table Laboratory");
        c.close();
       
        
        String sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary "
          + "from people as PL "
          + "inner join position as PS "
          + "on PL.posid = PS.id ";
        c = db.rawQuery(sqlQuery, null);
        logCursor(c, "inner join");
        c.close();
      }

      // вывод в лог данных из курсора
      void logCursor(Cursor c, String title) {
        if (c != null) {
          if (c.moveToFirst()) {
            Log.d(LOG_TAG, title + ". " + c.getCount() + " rows");
            StringBuilder sb = new StringBuilder();
            do {
              sb.setLength(0);
              for (String cn : c.getColumnNames()) {
                sb.append(cn + " = "
                    + c.getString(c.getColumnIndex(cn)) + "; ");
              }
              Log.d(LOG_TAG, sb.toString());
            } while (c.moveToNext());
          }
        } else
          Log.d(LOG_TAG, title + ". Cursor is null");
      }
*/
	
  }
	

