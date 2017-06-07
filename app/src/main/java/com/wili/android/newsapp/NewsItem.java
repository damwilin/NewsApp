package com.wili.android.newsapp;

import java.net.URL;

/**
 * Created by Damian on 6/6/2017.
 */

public class NewsItem {
    private String title;
    private String date;
    private String sectionName;
    private URL articleURL;

    public NewsItem(String title, String date, String sectionName, URL articleURL) {
        this.title = title;
        this.date = date;
        this.sectionName = sectionName;
        this.articleURL = articleURL;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getSectionName() {
        return sectionName;
    }

    public URL getArticleURL() {
        return articleURL;
    }
}
