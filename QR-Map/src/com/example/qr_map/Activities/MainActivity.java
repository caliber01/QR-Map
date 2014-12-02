package com.example.qr_map.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.qr_map.R;
import com.example.qr_map.Fragments.CameraFragment;
import com.example.qr_map.Fragments.LabListFragment;
import com.example.qr_map.Logic.ForMaxTestLabDataAccess;


public class MainActivity extends ActionBarActivity {

	private String MY_LOG ="log";
	private String MENU_ITEM_POSITION = "position";
	
	private String[] menuItems;
	private ListView mListView;
	private DrawerLayout mDrawerLayout;
	private CharSequence mTitle;
	private LinearLayout mSideLinearLayout;
	
	private ForMaxTestLabDataAccess data;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ActionBar ab = this.getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);
        
        menuItems = this.getResources().getStringArray(R.array.menu_items);
        mListView = (ListView) this.findViewById(R.id.lv_drawer);
        mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        mSideLinearLayout = (LinearLayout) this.findViewById(R.id.linear_drawer);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuItems);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
        mListView.setItemChecked(position, true);
        setTitle(menuItems[position]);
        mDrawerLayout.closeDrawer(mSideLinearLayout);
    }
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }
}


