package com.example.android.mygithubclient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

public class SearchFragment extends Fragment {

    private static final String URL_LINK = "https://api.github.com/users/{user}/repos";
    private String userInput;
    private EditText editText;
    private String outputLink;
    private Button searchButton;

    public SearchFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_layout,container,false);
        editText = rootView.findViewById(R.id.editText);
        searchButton = rootView.findViewById(R.id.button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtil.hideKeyboard(getActivity());
                ConnectivityManager connectionPossible = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectionPossible.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    userInput = editText.getText().toString().trim();
                    if(userInput.isEmpty()){
                        Toast toast = Toast.makeText(getActivity(), R.string.nothing_there,Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else{
                        outputLink = URL_LINK.replace("{user}", userInput);
                        RepositoryFragment repositoryFragment= new RepositoryFragment();
                        repositoryFragment.setArguments(buildBundle(outputLink, userInput));
                        android.support.v4.app.FragmentManager manager = getFragmentManager();
                        manager.beginTransaction().replace(R.id.view_pager, repositoryFragment,repositoryFragment.getTag()).addToBackStack(null).commit();
                    }
                } else {
                    Toast toast = Toast.makeText(getActivity(), R.string.no_connection,Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
        return rootView;
    }

    private Bundle buildBundle(String urlLink, String userName) {
        Bundle bundle = new Bundle();
        bundle.putString("urlLink", urlLink);
        bundle.putString("userName",userName);
        return bundle;
    }
}
