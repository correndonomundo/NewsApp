package com.example.android.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

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
            categoryView.setText(currentNews.getCategory());

            TextView newsTitleView = (TextView) listItemView.findViewById(R.id.news_title);
            newsTitleView.setText(currentNews.getNewsTitle());

            Date dateObject = new Date(currentNews.getTimeInMiliseconds());

            TextView dateView = (TextView) listItemView.findViewById(R.id.date);
            String formattedDate = formatDate(dateObject);
            dateView.setText(formattedDate);

            TextView timeView = (TextView) listItemView.findViewById(R.id.time);
            String formattedTime = formatTime(dateObject);
            timeView.setText(formattedTime);

            return listItemView;
        }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
        private String formatTime (Date dateObject){
            SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
            return timeFormat.format(dateObject);

        }
    }
