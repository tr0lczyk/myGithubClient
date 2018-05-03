package com.example.android.mygithubclient;

import android.os.Parcel;
import android.os.Parcelable;

public class Repository implements Parcelable {

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

    Repository(String fullName, String starsGiven, String avatar, String userLogin,
               String watchersCount, String forksCount, boolean openSource,
               String programmingLanguage, String issuesCount, String branchesType,
               String createdDate, String updatedDate, long sizeRepo, String name){

        this.fullName = fullName;
        this.starsGiven = starsGiven;
        this.avatar = avatar;
        this.userLogin = userLogin;
        this.watchersCount = watchersCount;
        this.forksCount = forksCount;
        this.openSource = openSource;
        this.programmingLanguage = programmingLanguage;
        this.issuesCount = issuesCount;
        this.branchesType = branchesType;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.sizeRepo = sizeRepo;
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStarsGiven() {
        return starsGiven;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getWatchersCount(){
        return watchersCount;
    }

    public String getForksCount() {
        return forksCount;
    }

    public boolean isOpenSource() {
        return openSource;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getIssuesCount() {
        return issuesCount;
    }

    public String getBranchesType() {
        return branchesType;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getUpdateDate() {
        return updatedDate;
    }

    public long getSizeRepo() {
        return sizeRepo;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fullName);
        dest.writeString(this.starsGiven);
        dest.writeString(this.avatar);
        dest.writeString(this.userLogin);
        dest.writeString(this.watchersCount);
        dest.writeString(this.forksCount);
        dest.writeByte(this.openSource ? (byte) 1 : (byte) 0);
        dest.writeString(this.programmingLanguage);
        dest.writeString(this.issuesCount);
        dest.writeString(this.branchesType);
        dest.writeString(this.createdDate);
        dest.writeString(this.updatedDate);
        dest.writeLong(this.sizeRepo);
        dest.writeString(this.name);
    }

    private Repository(Parcel in) {
        this.fullName = in.readString();
        this.starsGiven = in.readString();
        this.avatar = in.readString();
        this.userLogin = in.readString();
        this.watchersCount = in.readString();
        this.forksCount = in.readString();
        this.openSource = in.readByte() != 0;
        this.programmingLanguage = in.readString();
        this.issuesCount = in.readString();
        this.branchesType = in.readString();
        this.createdDate = in.readString();
        this.updatedDate = in.readString();
        this.sizeRepo = in.readLong();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Repository> CREATOR = new Parcelable.Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel source) {
            return new RepositoryBuilder().setIn(source).createRepository();
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };
}
