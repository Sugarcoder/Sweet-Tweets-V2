package com.codepath.apps.sweettweets.fragments;

import android.app.Fragment;
import android.app.FragmentManager;


public class PagerAdapter extends Fragment {
    final int PAGE_COUNT = 2;
    private final FragmentManager fm = null;
    private String tabTitles[] = new String[] { "Home", "Mentions" };
    private int position;



    public int getCount() {
        return PAGE_COUNT;
    }


    public PageFragmentHome getItem(int position) {
        return PageFragmentHome.newInstance(position + 1);
    }


    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
