package com.example.android.mygithubclient.util;

import org.junit.Test;
import static com.example.android.mygithubclient.util.DateUtils.isoDateToNormal;
import static junit.framework.Assert.assertEquals;

public class DateUtilsTest {

    @Test
    public void isoDateToNormalTestOne() {

        //given
        String validDate = "2017-12-28T14:56:37Z";

        //when
        String resultDate = isoDateToNormal(validDate);

        //then
        String expectedOutput = "28-12-2017";
        assertEquals(resultDate,expectedOutput);
    }

    @Test
    public void isoDateToNormalTestTwo(){
        //given
        String validDate = "2018-02-02T21:22:47Z";

        //when
        String resultDate = isoDateToNormal(validDate);

        //then
        String expectedOutput = "02-02-2018";
        assertEquals(resultDate,expectedOutput);
    }
    @Test
    public void isoDateToNormalTestThree(){
        //given
        String validDate = "2018-03-19T20:22:19Z";

        //when
        String resultDate = isoDateToNormal(validDate);

        //then
        String expectedOutput = "19-03-2018";
        assertEquals(resultDate,expectedOutput);
    }
}