package com.example.android.mygithubclient.util;

import java.text.DecimalFormat;

public class FileUtils {

    private FileUtils(){}

    public static String getFileSize(long size) {
        if (size <= 0){
            return "0";
        }
        String[] units = new String[] { "b", "kb", "mb", "gb", "tb" };
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
