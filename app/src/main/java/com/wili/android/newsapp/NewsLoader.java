package com.wili.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Damian on 6/7/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsItem>> {
    private String url;

    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsItem> loadInBackground() {
        if (url == null)
            return null;
        return Utils.fetchData(url);
    }
}