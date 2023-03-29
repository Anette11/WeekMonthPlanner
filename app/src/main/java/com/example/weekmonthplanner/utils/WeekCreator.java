package com.example.weekmonthplanner.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class WeekCreator {

    public List<DateItem> createDateItems() {
        List<DateItem> list;
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        switch (week) {
            case 2:
                list = createList(0);
                break;
            case 3:
                list = createList(-1);
                break;
            case 4:
                list = createList(-2);
                break;
            case 5:
                list = createList(-3);
                break;
            case 6:
                list = createList(4);
                break;
            case 7:
                list = createList(-5);
                break;
            case 1:
                list = createList(-6);
                break;
            default:
                list = new ArrayList<>();
                break;
        }
        return list;
    }

    private List<DateItem> createList(int startIndex) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE", Locale.ENGLISH);
        List<DateItem> list = new ArrayList<>();
        calendar.add(Calendar.DATE, startIndex);
        for (int i = startIndex; i <= (startIndex + 6); i++) {
            if (i != startIndex) calendar.add(Calendar.DATE, 1);
            list.add(new DateItem(
                    String.valueOf(calendar.get(Calendar.DATE)),
                    simpleDateFormat.format(calendar.getTime().getTime()).toUpperCase()));
        }
        return list;
    }
}