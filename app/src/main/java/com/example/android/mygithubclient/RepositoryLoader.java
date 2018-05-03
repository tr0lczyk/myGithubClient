package com.example.android.mygithubclient;

import android.content.Context;
import android.util.Log;
import java.util.List;

public class RepositoryLoader extends android.support.v4.content.AsyncTaskLoader<List<Repository>> {

    private static final String LOG = RepositoryLoader.class.getName();

    private String repositoryUrl;

    RepositoryLoader(Context context, String url){
        super(context);
        repositoryUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.e(LOG, "Loader started");
        forceLoad();
    }

    @Override
    public List<Repository> loadInBackground() {
        Log.e(LOG, "Loader inBackground");
        if(repositoryUrl == null){
            return null;
        }
        Log.e(LOG, "Loader fetching");
        return Networking.fetchMarketRecordData(repositoryUrl);
    }
}