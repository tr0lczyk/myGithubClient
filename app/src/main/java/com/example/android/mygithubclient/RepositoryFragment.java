package com.example.android.mygithubclient;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepositoryFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Repository>>  {


    public RepositoryFragment() {
        // Required empty public constructor
    }

    private RepositoryAdapter newAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_list,container,false);
        ListView listView = rootView.findViewById(R.id.listView);
        newAdapter = new RepositoryAdapter(getActivity(), new ArrayList<Repository>());
        listView.setAdapter(newAdapter);

        ConnectivityManager connectionPossible = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionPossible.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            int loaderId = 0;
            loaderManager.initLoader(loaderId, null, this);
        } else {
            Toast toast = Toast.makeText(getActivity(),R.string.no_connect,Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        return rootView;
    }

    @Override
    public Loader<List<Repository>> onCreateLoader(int id, Bundle args) {
        String urlLink = getArguments().getString("urlLink");
        return new RepositoryLoader(getActivity(), urlLink);
    }

    @Override
    public void onLoadFinished(Loader<List<Repository>> loader, List<Repository> data) {
        newAdapter.clear();
        newAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Repository>> loader) {
        newAdapter.clear();
    }

}
