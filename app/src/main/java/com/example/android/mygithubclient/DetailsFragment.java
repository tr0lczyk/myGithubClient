package com.example.android.mygithubclient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import static com.example.android.mygithubclient.util.DateUtils.isoDateToNormal;
import static com.example.android.mygithubclient.util.FileUtils.getFileSize;

public class DetailsFragment extends Fragment {

    public DetailsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_layout, container, false);

        Repository currentRepository = getArguments().getParcelable("object");

        ImageView imageView = rootView.findViewById(R.id.avatarImage);
        Picasso.get().load(currentRepository.getAvatar()).into(imageView);

        TextView ownerLogin = rootView.findViewById(R.id.ownerLogin);
        ownerLogin.setText(currentRepository.getUserLogin());

        TextView repositoryName = rootView.findViewById(R.id.repositoryName);
        repositoryName.setText(currentRepository.getName());

        TextView starsCount = rootView.findViewById(R.id.starsNumber);
        starsCount.setText(currentRepository.getStarsGiven());

        TextView watchersCount = rootView.findViewById(R.id.watchersNumber);
        watchersCount.setText(currentRepository.getWatchersCount());

        TextView forksCount = rootView.findViewById(R.id.forksNumber);
        forksCount.setText(currentRepository.getForksCount());

        TextView privacy = rootView.findViewById(R.id.typeOfRepo);
        privacy.setText(toRepositoryType(currentRepository.isOpenSource()));

        TextView language = rootView.findViewById(R.id.programmingDescription);
        language.setText(currentRepository.getProgrammingLanguage());

        TextView issuesCount = rootView.findViewById(R.id.issuesNumber);
        issuesCount.setText(currentRepository.getIssuesCount());

        TextView branchType = rootView.findViewById(R.id.branchType);
        branchType.setText(currentRepository.getBranchesType());

        TextView createdDate = rootView.findViewById(R.id.createDate);
        createdDate.setText(isoDateToNormal((currentRepository.getCreatedDate())));

        TextView sizeNumber = rootView.findViewById(R.id.sizeNumber);
        sizeNumber.setText(getFileSize(currentRepository.getSizeRepo()));

        TextView updatedDate = rootView.findViewById(R.id.updatedDate);
        updatedDate.setText(isoDateToNormal((currentRepository.getUpdateDate())));

        return rootView;
    }

    private String toRepositoryType(boolean isOpenSource){
        return isOpenSource ? "Private" : "Public";
    }
}
