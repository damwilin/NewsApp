package com.wili.android.newsapp;

/**
 * Created by Damian on 6/6/2017.
 */

public class NewsItem {
    private String title;
    private String date;
    private String sectionName;
    private String articleUrl;

    public NewsItem(String title, String date, String sectionName, String articleUrl) {
        this.title = title;
        this.date = date;
        this.sectionName = sectionName;
        this.articleUrl = articleUrl;
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

    public String getArticleUrl() {
        return articleUrl;
    }
}
