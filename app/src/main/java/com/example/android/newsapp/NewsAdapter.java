package com.example.android.newsapp;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    private static final String LOCATION_SEPARATOR = " | ";

    public NewsAdapter(Context context, List<News> newss) {
        super(context, 0, newss);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(

                    R.layout.news_list_item, parent, false);
        }

        News currentNews = getItem(position);

        TextView categoryView = (TextView) listItemView.findViewById(R.id.news_category);
        categoryView.setText("Category " + currentNews.getCategory());

        TextView newsTitleView = (TextView) listItemView.findViewById(R.id.news_title);
        newsTitleView.setText(currentNews.getNewsTitle());

        TextView authorView = (TextView) listItemView.findViewById(R.id.news_author);
        authorView.setText(TextUtils.join(", ", currentNews.getAuthors()));

        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        Date dateObject = new Date();
        try {
            dateObject = parser.parse(currentNews.getDateTime());
        } catch (Exception ex) {
        }

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateView.setText("Date" + formattedDate);

        return listItemView;


    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }


}
