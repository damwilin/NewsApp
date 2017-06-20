package com.wili.android.newsapp;

import android.content.Loader;
import android.os.Bundle;
import android.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<NewsItem>> {
    private static final int LOADER_ID = 1;
    @BindView(R.id.list_view)
    ListView listView;
    ArrayList<NewsItem> newsItems;
    private NewsAdapter newsAdapter;
    private LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        newsAdapter = new NewsAdapter(this, newsItems);
        listView.setAdapter(newsAdapter);
        if (Utils.isConnected()) {
            loaderManager = getLoaderManager();
            loaderManager.initLoader(LOADER_ID, null, this);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsItem currNewsItem = newsItems.get(position);
                Utils.openWebPage(MainActivity.this, currNewsItem);
            }
        });


    }

    @Override
    public Loader<ArrayList<NewsItem>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<NewsItem>> loader, ArrayList<NewsItem> data) {
        if (data != null || !data.isEmpty())
            newsAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<NewsItem>> loader) {
        newsAdapter.clear();
    }
}
