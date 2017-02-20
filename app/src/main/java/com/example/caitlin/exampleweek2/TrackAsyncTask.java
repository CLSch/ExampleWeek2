package com.example.caitlin.exampleweek2;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Caitlin on 20-02-17.
 */

public class TrackAsyncTask extends AsyncTask<String, Integer, String> {
    Context context;
    MainActivity mainAct;

    /** constructor */
    public TrackAsyncTask(MainActivity main) {
        this.mainAct = main;
        this.context = this.mainAct.getApplicationContext();
    }

    /** Lets the user know what's happening before all the data has been fetched. */
    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "searching for tracks...", Toast.LENGTH_SHORT).show();
    }

    /** Get the data from the API inputstream */
    @Override
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer("track", params);
    }

    /** Parse the streamdata that got returned from doInBackGround and put it in an array of
     * TrackData objects. */
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (result.length() == 0) {
            Toast.makeText(context, "Something went wrong getting the data from server", Toast.LENGTH_SHORT).show();
        }
        else {
            ArrayList<String> trackData = new ArrayList<>();
            try {
                // parse the data from the stream
                JSONObject trackStreamObj = new JSONObject(result);
                JSONObject resultsObj = trackStreamObj.getJSONObject("results");
                JSONObject trackMatches = resultsObj.getJSONObject("trackmatches");
                JSONArray tracksObj = trackMatches.getJSONArray("track");

                // get the track, artist and image url from all the search results
                for(int i = 0; i < tracksObj.length(); i++) {
                    JSONObject track = tracksObj.getJSONObject(i);
                    String trackName = track.getString("name");
                    String artistName = track.getString("artist");

                    // add the data from search results to the TrackData Arraylist
                    trackData.add(trackName + " - " + artistName);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // call trackStartIntent function from MainActivity
            this.mainAct.trackStartIntent(trackData);
        }
    }
}

