package com.example.qr_map.Activities;

import java.util.List;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qr_map.R;
import com.example.qr_map.Logic.DataAccess;
import com.example.qr_map.Models.Room;

public class SearchActivity extends ActionBarActivity implements OnItemClickListener{
	
	private ListView mListView;
	private DataAccess mDataAccess;
	
	private final String LAB_NUMBER_KEY = "number";
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
	}
	
	 @Override
	  protected void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    this.setContentView(R.layout.activity_search);
		    handleIntent(getIntent());
	    }

	    @Override
	    protected void onNewIntent(Intent intent) {
	        handleIntent(intent);
	    }

	    private void handleIntent(Intent intent) {

	        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	            String query = intent.getStringExtra(SearchManager.QUERY);
	            mDataAccess = new DataAccess(this,"qr.db");
	            this.getSupportActionBar().setTitle(query);
	            
	            List<Room> results = mDataAccess.FindByName(query);
	            Log.i("mylog", "LabList: "+results.toString());
			    mListView = (ListView) this.findViewById(R.id.lv_search);
			    mListView.setOnItemClickListener(this);
	            mListView.setAdapter(new SearchAdapter(results));
	        }
	    }

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long id) {
			Intent intent = new Intent(this,LabActivity.class);
			intent.putExtra(LAB_NUMBER_KEY, view.getTag().toString());
			startActivity(intent);
		}
		
		private class SearchAdapter extends BaseAdapter{
			private List<Room> searchResults;
			private LayoutInflater lInflater;
			
			public SearchAdapter(){
				
			}
			public SearchAdapter(List<Room> _searchResults){
				searchResults = _searchResults;
				lInflater = (LayoutInflater)
						getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return searchResults.size();
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return searchResults.get(position);
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				TextView view = (TextView)convertView;
				if(view == null){
					view = (TextView) lInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
				}
				
				Room r = searchResults.get(position);
				view.setText(r.getName());
				view.setTag(r.getNumber());
				return view;
			}
			
			
		}
}
