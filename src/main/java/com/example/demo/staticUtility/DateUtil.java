package com.example.demo.staticUtility;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final String today = sdf.format(new java.util.Date());

    public static Date getTodayDate(){
        return Date.valueOf(today);
    }
}
