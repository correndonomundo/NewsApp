package com.example.android.newsapp;

import java.util.ArrayList;
import java.util.List;

public class News {

    private String mCategory;
    private String mNewsTitle;
    private List<String> mAuthor;
    private String mDate;
    private String mUrl;


    public News(String category, String newsTitle, List<String> authors, String date, String url) {

        mCategory = category;
        mNewsTitle = newsTitle;
        mAuthor = authors;
        mDate = date;
        mUrl = url;
    }

    public String getCategory() {

        return mCategory;
    }
    public String getNewsTitle() {

        return mNewsTitle;

       }

       public List<String> getAuthors () {

        return mAuthor;
       }

     public String getDateTime() {

        return mDate;
    }

    public String getUrl() {

        return mUrl;
    }
}
