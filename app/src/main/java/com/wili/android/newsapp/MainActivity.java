package com.wili.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsItem>> {
    private static final int LOADER_ID = 1;
    private static final String REQUEST_LINK = "http://content.guardianapis.com/search?q=music&debates&api-key=test";
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.empty_view)
    TextView emptyView;
    private NewsAdapter newsAdapter;
    private ArrayList<NewsItem> newsItems;
    private LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        newsAdapter = new NewsAdapter(this, new ArrayList<NewsItem>());
        listView.setAdapter(newsAdapter);
        listView.setEmptyView(emptyView);
/**
 ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
 NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
 boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
 if (isConnected)
 getLoaderManager().initLoader() */

        if (Utils.isConnected(getBaseContext())) {
            loaderManager = getLoaderManager();
            loaderManager.initLoader(LOADER_ID, null, this);
        } else {
            progressBar.setVisibility(View.GONE);
            emptyView.setText(R.string.no_internet);
            Log.e(MainActivity.class.getSimpleName(), getString(R.string.no_internet));

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsItem currNewsItem = (NewsItem) parent.getItemAtPosition(position);
                Utils.openWebPage(MainActivity.this, currNewsItem);
            }
        });


    }

    @Override
    public Loader<List<NewsItem>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, REQUEST_LINK);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsItem>> loader, List<NewsItem> data) {
        progressBar.setVisibility(View.GONE);
        emptyView.setText(R.string.no_news);
        if (data != null) {
            newsAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsItem>> loader) {
        newsAdapter.clear();
    }
}
