package com.wili.android.newsapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.R.id.input;

/**
 * Created by Damian on 6/7/2017.
 */

public class Utils {
    private static final String LOG_TAG = Utils.class.getSimpleName();

    private Utils() {
    }
    public static ArrayList<NewsItem> fetchData(String requestURL){
        URL url = createURL(requestURL);
        try {
            String jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error creating jsonResponse", e);
        }
    }

    private static URL createURL(String requestURL) {
        URL url = null;
        try {
            url = new URL(requestURL);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error create URL", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null)
            return jsonResponse;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() ==200){
                inputStream = urlConnection.getInputStream();
                readFromStream(inputStream);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the JSON Result", e);
        }
        finally {
            if (urlConnection !=null)
                urlConnection.disconnect();
            if (inputStream != null)
                inputStream.close();
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line !=null){
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<NewsItem> parsingJson(String jsonResponse){
        ArrayList<NewsItem> newsItems= new ArrayList<NewsItem>();

        return newsItems;
    }
}
