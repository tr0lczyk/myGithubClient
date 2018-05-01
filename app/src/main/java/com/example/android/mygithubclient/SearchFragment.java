package com.example.android.mygithubclient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

public class SearchFragment extends Fragment {

    public SearchFragment() {}

    public String userInput;
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
                UIUtil.hideKeyboard(getActivity());
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                userInput = editText.getText().toString().trim();
                outputLink = urlLink.replace("{user}", userInput);
                RepositoryFragment repositoryFragment= new RepositoryFragment();
                repositoryFragment.setArguments(buildBundle(outputLink, userInput));
                android.support.v4.app.FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.view_pager, repositoryFragment,repositoryFragment.getTag()).addToBackStack(null).commit();

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
