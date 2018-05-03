package com.example.android.mygithubclient.util;

import org.junit.Test;

import static com.example.android.mygithubclient.util.FileUtils.getFileSize;
import static junit.framework.Assert.assertEquals;

public class FileUtilsTest {

    @Test
    public void getFileSizeTestOne() {

        //given
        long entryNumber = 20529;

        //when
        String resultNumber = getFileSize(entryNumber);

        //then
        String expectedOutput = "20 kb";
        assertEquals(resultNumber,expectedOutput);
    }

    @Test
    public void getFileSizeTestTwo() {

        //given
        long entryNumber = 123;

        //when
        String resultNumber = getFileSize(entryNumber);

        //then
        String expectedOutput = "123 b";
        assertEquals(resultNumber,expectedOutput);
    }

    @Test
    public void getFileSizeTestThree() {

        //given
        long entryNumber = 14581;

        //when
        String resultNumber = getFileSize(entryNumber);

        //then
        String expectedOutput = "14,2 kb";
        assertEquals(resultNumber,expectedOutput);
    }
}