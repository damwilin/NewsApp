package com.wili.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Damian on 6/7/2017.
 */

public class Utils {
    private static final String LOG_TAG = Utils.class.getSimpleName();

    private Utils() {
    }

    public static ArrayList<NewsItem> fetchData(String requestURL) {
        URL url = createURL(requestURL);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error creating jsonResponse", e);
        }
        return extractFeatureFromJson(jsonResponse);
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
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                readFromStream(inputStream);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the JSON Result", e);
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
            if (inputStream != null)
                inputStream.close();
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<NewsItem> extractFeatureFromJson(String jsonResponse) {
        ArrayList<NewsItem> newsItems = new ArrayList<NewsItem>();
        if (TextUtils.isEmpty(jsonResponse))
            return null;
        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray results = root.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject currResult = results.getJSONObject(i);
                String title = currResult.optString("webTitle");
                String date = currResult.optString("webPublicationDate");
                String section = currResult.optString("sectionName");
                String articleURL = currResult.optString("webUrl");
                newsItems.add(new NewsItem(title, date, section, articleURL));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the json result", e);
        }
        return newsItems;
    }

    public static void openWebPage(Context context, NewsItem currNewsItem) {
        Uri webpage = Uri.parse(currNewsItem.getArticleWebPage());
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(context.getPackageManager()) != null)
            context.startActivity(intent);
    }
}
