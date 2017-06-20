package com.wili.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Damian on 6/7/2017.
 */

public class NewsLoader extends AsyncTaskLoader<ArrayList<NewsItem>> {

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<NewsItem> loadInBackground() {
        return Utils.fetchData();
    }
}
