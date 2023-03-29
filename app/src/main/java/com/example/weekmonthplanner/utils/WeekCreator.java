package com.example.weekmonthplanner.utils;

import android.text.format.DateUtils;

import com.example.weekmonthplanner.R;

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
            case Calendar.MONDAY:
                list = createList(0);
                break;
            case Calendar.TUESDAY:
                list = createList(-1);
                break;
            case Calendar.WEDNESDAY:
                list = createList(-2);
                break;
            case Calendar.THURSDAY:
                list = createList(-3);
                break;
            case Calendar.FRIDAY:
                list = createList(4);
                break;
            case Calendar.SATURDAY:
                list = createList(-5);
                break;
            case Calendar.SUNDAY:
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
            int colorInt = R.color.black_medium;
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                colorInt = R.color.black_darker;
            }
            if (DateUtils.isToday(calendar.getTime().getTime())) {
                colorInt = R.color.green_light;
            }
            list.add(new DateItem(
                    String.valueOf(calendar.get(Calendar.DATE)),
                    simpleDateFormat.format(calendar.getTime().getTime()).toUpperCase(),
                    colorInt));
        }
        return list;
    }

    public int getExerciseIndex() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if (week == Calendar.MONDAY) return 1;
        if (week == Calendar.WEDNESDAY) return 2;
        if (week == Calendar.FRIDAY) return 3;
        return -1;
    }
}