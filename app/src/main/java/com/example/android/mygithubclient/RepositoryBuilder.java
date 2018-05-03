package com.example.android.mygithubclient;

import android.os.Parcel;

public class RepositoryBuilder {
    private String fullName;
    private String starsGiven;
    private String avatar;
    private String userLogin;
    private String watchersCount;
    private String forksCount;
    private boolean openSource;
    private String programmingLanguage;
    private String issuesCount;
    private String branchesType;
    private String createdDate;
    private String updatedDate;
    private long sizeRepo;
    private String name;
    private Parcel in;

    public RepositoryBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public RepositoryBuilder setStarsGiven(String starsGiven) {
        this.starsGiven = starsGiven;
        return this;
    }

    public RepositoryBuilder setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public RepositoryBuilder setUserLogin(String userLogin) {
        this.userLogin = userLogin;
        return this;
    }

    public RepositoryBuilder setWatchersCount(String watchersCount) {
        this.watchersCount = watchersCount;
        return this;
    }

    public RepositoryBuilder setForksCount(String forksCount) {
        this.forksCount = forksCount;
        return this;
    }

    public RepositoryBuilder setOpenSource(boolean openSource) {
        this.openSource = openSource;
        return this;
    }

    public RepositoryBuilder setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
        return this;
    }

    public RepositoryBuilder setIssuesCount(String issuesCount) {
        this.issuesCount = issuesCount;
        return this;
    }

    public RepositoryBuilder setBranchesType(String branchesType) {
        this.branchesType = branchesType;
        return this;
    }

    public RepositoryBuilder setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public RepositoryBuilder setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public RepositoryBuilder setSizeRepo(long sizeRepo) {
        this.sizeRepo = sizeRepo;
        return this;
    }

    public RepositoryBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RepositoryBuilder setIn(Parcel in) {
        this.in = in;
        return this;
    }

    public Repository createRepository() {
        return new Repository(fullName, starsGiven, avatar, userLogin, watchersCount, forksCount,
                openSource, programmingLanguage, issuesCount, branchesType, createdDate,
                updatedDate, sizeRepo, name);
    }
}