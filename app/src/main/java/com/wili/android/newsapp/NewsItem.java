package com.wili.android.newsapp;

/**
 * Created by Damian on 6/6/2017.
 */

public class NewsItem {
    private String title;
    private String date;
    private String sectionName;
    private String articleWebPage;

    public NewsItem(String title, String date, String sectionName, String articleWebPage) {
        this.title = title;
        this.date = date;
        this.sectionName = sectionName;
        this.articleWebPage = articleWebPage;
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

    public String getArticleWebPage() {
        return articleWebPage;
    }
}
