package com.example.android.mygithubclient;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    public SearchFragment() {}

    public String usersInput;
    private EditText editText;
    private String urlLink = "https://api.github.com/users/{user}/repos";
    public String outputLink;
    private Button searchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_layout,container,false);
        editText = rootView.findViewById(R.id.editText);
        searchButton = rootView.findViewById(R.id.button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usersInput = editText.getText().toString().trim();
                outputLink = urlLink.replace("{user}", usersInput);
                RepositoryFragment repositoryFragment= new RepositoryFragment();
                repositoryFragment.setArguments(buildBundle(outputLink));
                android.support.v4.app.FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.view_pager, repositoryFragment,repositoryFragment.getTag()).addToBackStack(null).commit();
            }
        });
        return rootView;
    }

    private Bundle buildBundle(String urlLink) {
        Bundle bundle = new Bundle();
        bundle.putString("urlLink", urlLink);
        return bundle;
    }
}
