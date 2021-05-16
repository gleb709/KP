package com.example.webproject.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTransformer {

    public static String dateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String docDate = dateFormat.format(date);
        return docDate;
    }

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date stringToDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("dd.MM.yyyy");
        Date docDate = null;
        try {
            docDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return docDate;
    }
}
