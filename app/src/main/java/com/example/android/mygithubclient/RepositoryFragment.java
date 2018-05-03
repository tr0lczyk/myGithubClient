package com.example.android.mygithubclient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RepositoryFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Repository>>  {

    private RepositoryAdapter newAdapter;
    private View rootView;
    private ArrayList<Repository> repositories;

    public RepositoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_list,container,false);
        ListView listView = rootView.findViewById(R.id.listView);
        repositories = new ArrayList<>();
        newAdapter = new RepositoryAdapter(getActivity(), repositories);
        listView.setAdapter(newAdapter);
        LoaderManager loaderManager = getLoaderManager();
        int loaderId = 0;
        loaderManager.initLoader(loaderId, null, this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Repository currentRepository = repositories.get(position);
                DetailsFragment detailsFragment= new DetailsFragment();
                detailsFragment.setArguments(buildBundle(currentRepository));
                android.support.v4.app.FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.view_pager, detailsFragment,detailsFragment.getTag()).addToBackStack(null).commit();
            }
        });
        return rootView;
    }

    private Bundle buildBundle(Repository repository){
        Bundle bundle = new Bundle();
        bundle.putParcelable("object",repository);
        return bundle;
    }

    private void alertDialog(){
        String user = getArguments().getString("userName");
        String[] answers = {user + getString(R.string.not_a_user), getString(R.string.likes) + user + getString(R.string.forbidden),
                user + getString(R.string.wrong), getString(R.string.spoon) + user + getString(R.string.dot)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.error);
        Random random = new Random();
        int shot = random.nextInt(4);
        builder.setMessage(answers[shot]);
        builder.setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SearchFragment searchFragment= new SearchFragment();
                android.support.v4.app.FragmentManager manager = getFragmentManager();
                assert manager != null;
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
            repositoryFullfill(data);

        } else {
            TextView owner = rootView.findViewById(R.id.repository_owner);
            owner.setText(R.string.long_face);
            alertDialog();
        }
    }

    private void repositoryFullfill(List<Repository> data) {
        String userName = getArguments().getString("userName");
        TextView owner = rootView.findViewById(R.id.repository_owner);
        owner.setText(userName + getString(R.string.reposs));
        View progressBar = rootView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        newAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Repository>> loader) {
        newAdapter.clear();
    }
}
