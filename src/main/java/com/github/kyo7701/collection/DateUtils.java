package com.github.kyo7701.collection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Author:Mr.Cris
 * Date:2021-01-14 21:27
 *
 * @description
 */
public class DateUtils {

    public static final int ONE_DAY = 24 * 60 * 60 * 1000;

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    static List<String> listDays(Date startTime,Date endTime) {
        if (startTime ==null || endTime == null) {
            return Collections.emptyList();
        }
        long startTimestamp = startTime.getTime();
        long endTimestamp = endTime.getTime();
        long duration = endTimestamp - startTimestamp;
        if (duration < ONE_DAY) {
            return Collections.emptyList();
        }
        long days = duration / ONE_DAY;
        long mod = duration % ONE_DAY;
        if (mod != 0) {
            days += 1;
        }
        List<String> result = new ArrayList<>();

        for (int i = 0; i < days; i++) {
            result.add(toDateStr(startTimestamp + (long) i * ONE_DAY));
        }
        return result;
    }

    public static String toDateStr(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        return simpleDateFormat.format(new Date(timestamp));
    }


    public static void main(String[] args) {
        Date startTime = new Date(1609430400000L);
        Date endTime = new Date(1610631620000L);
        List<String> days = listDays(startTime,endTime);
        for (String day : days) {
            System.out.println(day);
        }

    }





}
