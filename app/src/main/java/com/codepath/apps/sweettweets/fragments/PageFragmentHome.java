package com.codepath.apps.sweettweets.fragments;


import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.codepath.apps.sweettweets.R;
import com.codepath.apps.sweettweets.TweetEndlessScrollListener;
import com.codepath.apps.sweettweets.TwitterApplication;
import com.codepath.apps.sweettweets.TwitterClient;
import com.codepath.apps.sweettweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

import static com.codepath.apps.sweettweets.R.id.lvTweets;


// In this case, the fragment displays simple text based on the page
public class PageFragmentHome extends TweetListFragment {

    public static final String Home = "Home";

    // private int mPage;

    private TwitterClient client;
    private ListView lvHomeTweets;


    public PageFragmentHome() {
    }


    public static PageFragmentHome newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(Home, page);
        PageFragmentHome fragment = new PageFragmentHome();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        // mPage = getArguments().getInt(Home);
        super.onCreate(savedInstanceState);


        // Get the client.
        client = TwitterApplication.getRestClient();
        // This is important because we can get the singleton client (access the same data across all activities)


        if (savedInstanceState == null) {
            // Access the fragment.
            // fragment_tweet_list = (TweetListFragment) getFragmentManager().findFragmentById(R.id.lvTweets);
        }


        // Populate the timeline.
        populateTimeline();


        // Attach the listener to the AdapterView onCreate
        AbsListView lvTweets = null;

        /* if (lvTweets != null) {
            lvTweets.setOnScrollListener(new TweetEndlessScrollListener() {

                @Override
                public void onLoadMore(int page, int totalItemsCount) {
                    // Triggered only when new data needs to be appended to the list
                    // Add whatever code is needed to append new items to your AdapterView



                    populateTimeline();
                    // or customLoadMoreDataFromApi(totalItemsCount);
                }
            });
        }  */

    }



    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tweet_list, parent, false);
        lvHomeTweets = (ListView) view.findViewById(lvTweets);
        lvHomeTweets.setAdapter(aTweets);
        // tvHome.setText("Home");  // + mPage
        return view;
    }



    private void populateTimeline() {
        long max_id = 0;
        client.getPageFragmentHome(max_id, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                // Log.d("Debug", json.toString());


                // Deserialize JSON
                // Create Models and add them to the adapter

                // Load the Models into the ListView


                aTweets.addAll(Tweet.fromJSONArray(json));
                aTweets.notifyDataSetChanged();

                // max_id = fragmentTweetList.getItem(fragmentTweetList.getCount() - 1).getUid();

            }



            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }

        });


    }


}



