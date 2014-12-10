package com.example.qr_map.Activities;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.qr_map.R;
import com.example.qr_map.Fragments.CameraFragment;
import com.example.qr_map.Fragments.LabListFragment;
import com.example.qr_map.Logic.DataAccess;

public class MainActivity extends ActionBarActivity {

	private String MY_LOG ="log";
	private String MENU_ITEM_POSITION = "position";
	
	private String DR_TEXT = "text";
	private String DR_ICON = "icon";
	private String DR_NUMBER = "number";
	
	private String[] menuItems;
	private int[] menuIcons = {R.drawable.ic_list_grey600_36dp,
			R.drawable.ic_star_rate_grey600_36dp,
			R.drawable.ic_history_grey600_36dp};
	private ListView mListView;
	private DrawerLayout mDrawerLayout;
	private CharSequence mTitle;
	private LinearLayout mSideLinearLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	
	private DataAccess mDataAccess;
	
	public MainActivity() {
		this.mDataAccess = new DataAccess();
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ActionBar ab = this.getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);//R.drawable.ic_menu_white_24dp);
        ab.setHomeButtonEnabled(true);
        
        menuItems = this.getResources().getStringArray(R.array.menu_items);
        mListView = (ListView) this.findViewById(R.id.lv_drawer);
        mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        mSideLinearLayout = (LinearLayout) this.findViewById(R.id.linear_drawer);
        
        
        //setting nav drawer
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                 
                mDrawerLayout,        
                R.string.app_name,
                R.string.app_name  
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        int[] drawerCounters = {mDataAccess.CountAll(),mDataAccess.CountFavourites(),mDataAccess.CountHistory()};
        ArrayList<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i< 3; i++)
        {
        	HashMap<String,Object> m = new HashMap<String,Object>();
        	m.put(DR_TEXT, menuItems[i]);
        	m.put(DR_ICON, menuIcons[i]);
        	m.put(DR_NUMBER,drawerCounters[i]);
        	data.add(m);
        }
        String[] from = {DR_TEXT,DR_ICON};
        int[] to = {R.id.drawer_text,R.id.drawer_icon};
        SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.drawer_list_item,from,to);
        
        
        //Creating header  and footer
        View header = getLayoutInflater().inflate(R.layout.drawer_header, null);
        mListView.addHeaderView(header,"",false);
        mListView.setAdapter(adapter);
        

		Fragment fragment = new CameraFragment();
		FragmentTransaction frTrans = this.getSupportFragmentManager().beginTransaction();
        frTrans.add(R.id.content_frame, fragment);
        frTrans.commit();
        
        mListView.setOnItemClickListener( new DrawerItemClickListener());
               
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
     // Associate searchable configuration with the SearchView
        SearchManager searchManager =
               (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private class DrawerItemClickListener implements ListView.OnItemClickListener
    {
    	@Override
        public void onItemClick(AdapterView parent, View view, int position, long id)
    	{
            selectItem(position);
        }
    }
    
    private void selectItem(int position)
    {
    	Bundle bundle = new Bundle();
    	bundle.putInt(MENU_ITEM_POSITION, position);
    	
    	//Создаем фрагмент, передавая ему  информацию
		Fragment fragment = new LabListFragment();
		fragment.setArguments(bundle);
		
		//Меняем старый экран на новый 
		FragmentTransaction frTrans = this.getSupportFragmentManager().beginTransaction();
        frTrans.replace(R.id.content_frame, fragment);
        frTrans.commit();

        //Выделяем выбранный и закрываем навменю
        setTitle(menuItems[position-1]);
        mDrawerLayout.closeDrawer(mSideLinearLayout);
    }
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
}


