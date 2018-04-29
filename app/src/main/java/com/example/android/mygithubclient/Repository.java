package com.example.android.mygithubclient;

public class Repository implements Comparable<Repository> {

    private String fullName;

    private String starsGiven;

    public Repository(String fullName, String starsGiven){
        this.fullName = fullName;
        this.starsGiven = starsGiven;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStarsGiven() {
        return starsGiven;
    }

    @Override
    public int compareTo( Repository repository) {
        return fullName.compareTo(repository.fullName);
    }
}
