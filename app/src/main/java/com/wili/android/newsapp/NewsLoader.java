package com.wili.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by Damian on 6/7/2017.
 */

public class NewsLoader extends AsyncTaskLoader {

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Object loadInBackground() {
        return null;
    }
}
