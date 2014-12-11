package com.example.qr_map.Activities;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.qr_map.R;
import com.example.qr_map.Adapters.LabInfoAdapter;
import com.example.qr_map.Fragments.LabTabFragment;
import com.example.qr_map.Logic.DataAccess;
import com.example.qr_map.Models.Laboratory;
import com.example.qr_map.Views.SlidingTabLayout;

public class LabActivity extends ActionBarActivity {
	
	private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private String LAB_NUMBER_KEY = "number";
    
    private Laboratory lab;
    
    private String[] tabTitles;
    
    private FrameLayout mFrameLayout;
	
	 @Override
    public void onCreate(Bundle savedInstanceState) {
		 
		 super.onCreate(savedInstanceState);
		 this.setContentView(R.layout.activity_lab);
		 
		String labNumber = this.getIntent().getExtras().getString(LAB_NUMBER_KEY);
		Log.i("mytaf","in lbaactivity: "+labNumber);
		setTitle(labNumber);
		lab = (Laboratory)((new DataAccess(this,"qr.db").GetRoom(labNumber)));
		tabTitles = this.getResources().getStringArray(R.array.lab_tab_titles);
		
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter(this));

        // Give the SlidingTabLayout the ViewPager, this must be 
        // done AFTER the ViewPager has had it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setSelectedIndicatorColors(R.color.primary);
    }
	 
	 
	 static class SamplePagerItem {
	        private final CharSequence mTitle;
	        private final int mIndicatorColor;
	        private final int mDividerColor;
	 
	        SamplePagerItem(CharSequence title, int indicatorColor, int dividerColor) {
	            mTitle = title;
	            mIndicatorColor = indicatorColor;
	            mDividerColor = dividerColor;
	        }
	 
	        /**
	         * @return A new {@link Fragment} to be displayed by a {@link ViewPager}
	         */
	 
	        /**
	         * @return the title which represents this tab. In this sample this is used directly by
	         * {@link android.support.v4.view.PagerAdapter#getPageTitle(int)}
	         */
	        CharSequence getTitle() {
	            return mTitle;
	        }
	 
	        /**
	         * @return the color to be used for indicator on the {@link SlidingTabLayout}
	         */
	        int getIndicatorColor() {
	            return mIndicatorColor;
	        }
	 
	        /**
	         * @return the color to be used for right divider on the {@link SlidingTabLayout}
	         */
	        int getDividerColor() {
	            return mDividerColor;
	        }
	    }
	 
	 
	 
	 class SamplePagerAdapter extends PagerAdapter {

		 private Context context;
		 public SamplePagerAdapter(Context _context){
			 super();
			 context = _context;
		 }
	        /**
	         * Return the number of pages to display
	         */
	        @Override
	        public int getCount() {
	            return 3;
	        }
	        
	        /**
	         * Return true if the value returned from is the same object as the View
	         * added to the ViewPager.
	         */
	        @Override
	        public boolean isViewFromObject(View view, Object o) {
	            return o == view;
	        }

	        /**
	         * Return the title of the item at position. This is important as what
	         * this method returns is what is displayed in the SlidingTabLayout.
	         */
	        @Override
	        public CharSequence getPageTitle(int position) {
	            return tabTitles[position];
	        }

	        /**
	         * Instantiate the View which should be displayed at position. Here we
	         * inflate a layout from the apps resources and then change the text
	         * view to signify the position.
	         */
	        @Override
	        public Object instantiateItem(ViewGroup container, int position) {
	            // Inflate a new layout from our resources
	        	View view = null;
	        	view = getLayoutInflater().inflate(R.layout.lab_info_fragment,
	        											container, false);
	        	RecyclerView recView = (RecyclerView) view.findViewById(R.id.cardList);
	    		recView.setHasFixedSize(true);
	            LinearLayoutManager llm = new LinearLayoutManager(context);
	            llm.setOrientation(LinearLayoutManager.VERTICAL);
	            recView.setLayoutManager(llm);
	            
	            LabInfoAdapter adapter = null;
	            
	            switch(position){
	            case 0:
	            	adapter = new LabInfoAdapter(lab,R.array.lab_info_titles,context);
	            	break;
	            case 1:
	            	adapter = new LabInfoAdapter(lab,R.array.lab_sponsor_titles,context);
	            	break;
	            case 2:
	            	adapter = new LabInfoAdapter(lab,R.array.lab_equipment_titles,context);
	            	break;
	            }
	            recView.setAdapter(adapter);
	            // Add the newly created View to the ViewPager
	            container.addView(view);

	            // Return the View
	            return view;
	        }

	        /**
	         * Destroy the item from the ViewPager. In our case this is simply
	         * removing the View.
	         */
	        @Override
	        public void destroyItem(ViewGroup container, int position, Object object) {
	            container.removeView((View) object);
	        }
	    }	
}
