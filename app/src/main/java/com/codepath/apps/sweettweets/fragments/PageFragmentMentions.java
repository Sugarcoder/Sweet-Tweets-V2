package com.codepath.apps.sweettweets.fragments;


import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.codepath.apps.sweettweets.R;
import com.codepath.apps.sweettweets.TweetEndlessScrollListener;
import com.codepath.apps.sweettweets.TwitterApplication;
import com.codepath.apps.sweettweets.TwitterClient;
import com.codepath.apps.sweettweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;



// In this case, the fragment displays simple text based on the page

public class PageFragmentMentions extends TweetListFragment {

    public static final String Mentions = "Mentions";

    // private int mPage;

    private TwitterClient client;
    private TweetListFragment fragmentTweetList;


    public static PageFragmentMentions newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(Mentions, page);
        PageFragmentMentions fragment = new PageFragmentMentions();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // mPage = getArguments().getInt(Mentions);



        // Get the client.
        client = TwitterApplication.getRestClient();
        // This is important because we can get the singleton client (access the same data across all activities)


        // Populate the timeline.
        populateTimeline();


        if (savedInstanceState == null) {
            // Access the fragment.
            fragmentTweetList = (TweetListFragment) getFragmentManager().findFragmentById(R.id.lvTweets);
        }




        // Attach the listener to the AdapterView onCreate
        AbsListView lvTweets = null;

        if (lvTweets != null) {
            lvTweets.setOnScrollListener(new TweetEndlessScrollListener() {

                @Override
                public void onLoadMore(int page, int totalItemsCount) {
                    // Triggered only when new data needs to be appended to the list
                    // Add whatever code is needed to append new items to your AdapterView
                    populateTimeline();
                    // or customLoadMoreDataFromApi(totalItemsCount);
                }
            });
        }

    }



    private void populateTimeline() {
        long max_id = 0;
        client.getPageFragmentMentions(max_id, new JsonHttpResponseHandler() {
            // Success


            public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONArray json) {
                Log.d("Debug", json.toString());


                // Deserialize JSON

                // Create Models and add them to the adapter

                // Load the Models into the ListView
                fragmentTweetList.addAll(Tweet.fromJSONArray(json));     // 'aTweets' is the old one, instead of 'fragmentTweetList'
                // max_id = fragmentTweetList.getItem(fragmentTweetList.getCount() - 1).getUid();
            }


            // Failure

            public void onFailure(int statusCode, PreferenceActivity.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("Debug", errorResponse.toString());
            }

        });
    }



    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tweet_list, parent, false);
        // TextView tvMentions = (TextView) view.findViewById(R.id.tvMentions);
        // tvMentions.setText("Mentions");   // + mPage
        return view;
    }
}



