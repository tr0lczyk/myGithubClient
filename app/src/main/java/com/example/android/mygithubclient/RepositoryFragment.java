package com.example.android.mygithubclient;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepositoryFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Repository>>  {


    public RepositoryFragment() {
        // Required empty public constructor
    }

    private RepositoryAdapter newAdapter;
    private View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_list,container,false);
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

    public void alertDialog(){
        String user = getArguments().getString("userName");
        if(user.isEmpty()){
            user = "\"Nothing there\"";
        }
        String[] answers = {user + " is not a user you are looking for...", "The likes of " + user + " are forbidden in this land!",
                user + "? More like WRONG USERNAME !!!", "There is no spoon. And there is no " + user + "."};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Error 404");
        Random random = new Random();
        int shot = random.nextInt(4);
        builder.setMessage(answers[shot]);
        builder.setPositiveButton("Search again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SearchFragment searchFragment= new SearchFragment();
                android.support.v4.app.FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.view_pager, searchFragment,searchFragment.getTag()).commit();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public Loader<List<Repository>> onCreateLoader(int id, Bundle args) {
        String urlLink = getArguments().getString("urlLink");
        return new RepositoryLoader(getActivity(), urlLink);
    }

    @Override
    public void onLoadFinished(Loader<List<Repository>> loader, List<Repository> data) {
        newAdapter.clear();
        if(data != null && !data.isEmpty()){
            String userName = getArguments().getString("userName");
            TextView owner = rootView.findViewById(R.id.repository_owner);
            owner.setText(userName + "'s repositories:");
            View progressBar = rootView.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);
            newAdapter.addAll(data);
        } else {
            TextView owner = rootView.findViewById(R.id.repository_owner);
            owner.setText(":(");
            alertDialog();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Repository>> loader) {
        newAdapter.clear();
    }

}
