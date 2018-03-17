package com.example.android.theguardianapplication;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.theguardianapplication.models.Guardian;

import java.util.ArrayList;

public class NewsAppLoader extends AsyncTaskLoader<ArrayList<Guardian>> {
    String url;

    public NewsAppLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public ArrayList<Guardian> loadInBackground() {

        return Utils.fetchNewsData(url);
    }
}