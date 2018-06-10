package com.example.android.newsapp;

public class News {

    private String mCategory;
    private String mNewsTitle;
    private long mTimeInMiliseconds;
    private String mUrl;


    public News (String category, String newsTitle, long timeInMiliseconds, String url){

        mCategory = category;
        mNewsTitle = newsTitle;
        mTimeInMiliseconds = timeInMiliseconds;
        mUrl = url;
    }

    public String getCategory(){

        return mCategory;
    }

    public String getNewsTitle(){

        return mNewsTitle;
    }

    public long getTimeInMiliseconds(){

        return mTimeInMiliseconds;
    }

    public String getUrl(){

        return mUrl;
    }
}
