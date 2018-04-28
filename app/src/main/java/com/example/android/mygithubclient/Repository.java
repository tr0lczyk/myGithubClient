package com.example.android.mygithubclient;

public class Repository {

    private String fullName;

    private int starsGiven;

    public Repository(String fullName, int starsGiven){
        this.fullName = fullName;
        this.starsGiven = starsGiven;
    }

    public String getFullName() {
        return fullName;
    }

    public int getStarsGiven() {
        return starsGiven;
    }
}
