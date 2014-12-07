package com.example.qr_map.Logic;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

public class LocalHelper {
	//SharedPreferences sPref;
	
	/*void saveText() {
	    sPref = getPreferences(MODE_PRIVATE);
	    Editor ed = sPref.edit();
	    ed.putString(SAVED_TEXT, etText.getText().toString());
	    ed.commit();
	    Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
	  }
	  
	  void loadText() {
	    sPref = getPreferences();
	    String savedText = sPref.getString(SAVED_TEXT, "");
	    etText.setText(savedText);
	    Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
	  }*/
	
	public static HashSet read(String fileName)throws Exception
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
		HashSet<String> hs = (HashSet<String>) ois.readObject();
		return hs;
	}
	public static void write(String fileName,HashSet hs)throws Exception
	{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
		oos.writeObject(hs);
	}
	public static boolean add(String fileName,String value)throws Exception
	{
		HashSet h = read(fileName);
		boolean result = h.add(value);
		write(fileName,h);
		return result;
	}

}
