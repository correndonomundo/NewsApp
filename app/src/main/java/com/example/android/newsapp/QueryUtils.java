    package com.example.android.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {

    private static final String LOG_TAG =  QueryUtils.class.getSimpleName();

    private QueryUtils() {

    }

        public static List<News> fetchNewsData (String requestUrl){

            URL url = createUrl(requestUrl);

            String jsonResponse = null;

            try {
                jsonResponse = makeHttpRequest(url);

            } catch (IOException e) {
                Log.e(LOG_TAG, "Probem making the HTTP request.", e);
            }

            List<News> newss = extractResponseFromJson(jsonResponse);

            return newss;
        }

        private static URL createUrl (String stringUrl){
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException e) {
                Log.e(LOG_TAG, "Problem building the URL", e);
            }
            return url;
        }

        private static String makeHttpRequest(URL url) throws IOException{
            String jsonResponse = "";
            if (url == null){
                return jsonResponse;

            }

            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(1000 /*miliseconds*/);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
                    Log.e(LOG_TAG, "Error response code" + urlConnection.getResponseCode());
                }
            } catch (IOException e){
                    Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
                }finally{
                    if (urlConnection != null){
                     urlConnection.disconnect();
                     }
                     if(inputStream != null){
                     inputStream.close();
                     }
                }

                return jsonResponse;

    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
            line = reader.readLine();
            }
        }
        return output.toString();
    }
    private static List<News> extractResponseFromJson (String newsJSON){
        if(TextUtils.isEmpty(newsJSON)){
            return null;
        }
    List<News> newss = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(newsJSON);
            JSONArray NewsArray = baseJsonResponse.getJSONArray("response");

            for (int i = 0; i < NewsArray.length(); i++) {

                JSONObject currentNews = NewsArray.getJSONObject(i);

                JSONObject results = currentNews.getJSONObject("results");

                String webTitle = results.getString("webTitle");

                String sectionName = results.getString("sectionName");

                long webPublicationDate = results.getLong("webPublicationDate");

                String url = results.getString("url");

                News news = new News(webTitle, sectionName, webPublicationDate, url);

                newss.add(news);
            }
        }catch (JSONException e) {
                Log.e("QueryUtils", "Problem parsing the news JSON results", e);
            }
            return newss;
        }
}
