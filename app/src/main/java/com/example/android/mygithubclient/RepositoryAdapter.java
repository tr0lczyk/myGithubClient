package com.example.android.mygithubclient;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RepositoryAdapter extends ArrayAdapter<Repository> {

    public RepositoryAdapter(Activity context, ArrayList<Repository> repositories){
        super(context,0,repositories);
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

            Repository currentRepository = getItem(position);

            TextView repositoryName = listItemView.findViewById(R.id.textView5);
            repositoryName.setText(currentRepository.getFullName());

            TextView starNumber = listItemView.findViewById(R.id.textView6);
            starNumber.setText(currentRepository.getStarsGiven());
        }
        return listItemView;
    }
}
