package com.example.qr_map.Activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.ListView;

import com.example.qr_map.R;
import com.example.qr_map.Logic.ForMaxTestLabDataAccess;
import com.example.qr_map.Models.Room;

public class SearchActivity extends ActionBarActivity {
	
	private ListView mListView;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
	}
	
	 @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.setContentView(R.layout.activity_search);
	    handleIntent(getIntent());
	    
	    mListView = (ListView) this.findViewById(R.id.lv_search);
	    }

	    @Override
	    protected void onNewIntent(Intent intent) {
	        handleIntent(intent);
	    }

	    private void handleIntent(Intent intent) {

	        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	            String query = intent.getStringExtra(SearchManager.QUERY);
	            ForMaxTestLabDataAccess data = new ForMaxTestLabDataAccess();
	            this.getSupportActionBar().setTitle(query);
	            Room[] results = data.FindByName(query).toArray(new Room[]{});
	            String[] searchRes = new String[results.length];
	            for(int i = 0 ; i < results.length; i++)
	            {
	            	searchRes[i] = results[i].getName();
	            }
	            //mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1));
	        }
	    }
}
