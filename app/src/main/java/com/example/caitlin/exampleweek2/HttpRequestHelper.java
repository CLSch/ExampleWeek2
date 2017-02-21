package com.example.caitlin.exampleweek2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Caitlin on 20-02-17.
 */

public class HttpRequestHelper {
    private static final String API_KEY = "0ca01e4b23a3fb3304c5a58d32735bda";
    private static final String trackUrl1 = "http://ws.audioscrobbler.com/2.0/?method=track.search&track=";
    private static final String generalUrl2 = "&api_key=" + API_KEY + "&format=json";

    /** Get the inputstream from the API. */
    protected static synchronized String downloadFromServer(String datatype, String... params) {
        String completeUrlString = "";
        String result = "";
        String chosenTag = params[0];

        completeUrlString = trackUrl1 + chosenTag + generalUrl2;

        // make an URL object out of the url string
        URL url = null;
        try {
            url = new URL(completeUrlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection connect;
        if (url != null) {
            try {
                connect = (HttpURLConnection) url.openConnection();
                connect.setRequestMethod("GET");

                // read the returned data in from the API
                Integer responseCode = connect.getResponseCode();
                if (responseCode >= 200 && responseCode < 300) {
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                    String line;
                    while ((line = bReader.readLine()) != null) {
                        result += line;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
