package com.wili.android.newsapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Damian on 6/6/2017.
 */

public class NewsAdapter extends ArrayAdapter<NewsItem> {
    private View listView;

    public NewsAdapter(@NonNull Context context, @NonNull List<NewsItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        listView = convertView;
        ViewHolder holder;
        if (listView == null)
            listView = LayoutInflater.from(getContext()).inflate(R.layout.item,parent,false);
        NewsItem currNews = getItem(position);
        Context context = getContext();
        holder = new ViewHolder(listView);
        holder.title.setText(currNews.getTitle());
        holder.date.setText(currNews.getDate());
        holder.section.setText(currNews.getSectionName());

        return listView;
    }
    static class ViewHolder{
        @BindView(R.id.title) TextView title;
        @BindView(R.id.date) TextView date;
        @BindView(R.id.section_name) TextView section;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }

    }
}
