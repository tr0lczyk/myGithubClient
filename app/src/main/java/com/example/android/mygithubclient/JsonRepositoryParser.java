package com.example.android.mygithubclient;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonRepositoryParser {

    public JsonRepositoryParser(){}

    public static Repository parseRepository(JSONObject jsonRepository) throws JSONException {
            String fullName = jsonRepository.getString("full_name");
            String starsGiven = jsonRepository.getString("stargazers_count");
            String watchersCount = jsonRepository.getString("watchers_count");
            String forksCount = jsonRepository.getString("forks_count");
            boolean openSource = jsonRepository.getBoolean("private");
            String programmingLanguage = jsonRepository.getString("language");
            String issuesCount = jsonRepository.getString("open_issues_count");
            String branchesType = jsonRepository.getString("default_branch");
            String createdDate = jsonRepository.getString("created_at");
            String updatedDate = jsonRepository.getString("updated_at");
            long sizeRepo = jsonRepository.getLong("size");
            String name = jsonRepository.getString("name");
            JSONObject owner = jsonRepository.getJSONObject("owner");
            String avatar = owner.getString("avatar_url");
            String userLogin = owner.getString("login");
            return new RepositoryBuilder().setFullName(fullName).setStarsGiven(starsGiven)
                    .setAvatar(avatar).setUserLogin(userLogin).setWatchersCount(watchersCount)
                    .setForksCount(forksCount).setOpenSource(openSource)
                    .setProgrammingLanguage(programmingLanguage).setIssuesCount(issuesCount)
                    .setBranchesType(branchesType).setCreatedDate(createdDate)
                    .setUpdatedDate(updatedDate).setSizeRepo(sizeRepo).setName(name)
                    .createRepository();
    }
}
