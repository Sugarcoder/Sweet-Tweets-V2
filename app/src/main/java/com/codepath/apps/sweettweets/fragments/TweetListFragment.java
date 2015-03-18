package com.codepath.apps.sweettweets.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.sweettweets.R;
import com.codepath.apps.sweettweets.TweetsArrayAdapter;
import com.codepath.apps.sweettweets.models.Tweet;

import java.util.ArrayList;
import java.util.List;


public class TweetListFragment extends Fragment {


    private TweetsArrayAdapter aTweets; // Instantiating the objects


    // INFLATE LOGIC

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweet_list, parent, false);


        // Find the ListView
        ListView lvTweets = (ListView) v.findViewById(R.id.lvTweets);

        // Connect the adapter to the listview
        lvTweets.setAdapter(aTweets);

        return v;
    }



    // CREATION LIFECYCLE EVENT


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Create the array list (which is the data source)
        ArrayList<Tweet> tweets = new ArrayList<>();

        // Construct the adapter from the data source
        aTweets = new TweetsArrayAdapter(getActivity(), tweets);



    }


    public void addAll(List<Tweet> tweets) {
        aTweets.addAll(tweets);
    }

}
