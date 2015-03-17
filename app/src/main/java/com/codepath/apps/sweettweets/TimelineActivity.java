package com.codepath.apps.sweettweets;

import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.sweettweets.fragments.PageFragmentHome;
import com.codepath.apps.sweettweets.fragments.PageFragmentMentions;



public class TimelineActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#55ACEE")));
        actionBar.setIcon(R.drawable.ic_iconpeople);



        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {


            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Fragment getItem(int position) {
                return null;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return false;
            }
        });



        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public class PagerAdapter extends FragmentPagerAdapter {
        // final int PAGE_COUNT = 2;
        private String tabTitles[] = { "Home", "Mentions" };



        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }



        public int getCount() {
            return tabTitles.length;
        }


        public Fragment getItem(int position) {
            if (position == 0) {
                return new PageFragmentHome();
            } else if (position == 1) {
                return new PageFragmentMentions();
            } else {
                return null;
            }
        }


        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }

    }



}


