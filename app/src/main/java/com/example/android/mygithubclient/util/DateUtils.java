package com.example.android.mygithubclient.util;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private DateUtils(){}

    public static String isoDateToNormal(String isoDate){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date date = new Date();
        try{
            date = sd1.parse(isoDate.replaceAll("Z$", "+0000"));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sd2 = new SimpleDateFormat("dd-MM-yyyy");
        return sd2.format(date);
    }
}
