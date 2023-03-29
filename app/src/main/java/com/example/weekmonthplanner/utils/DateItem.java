package com.example.weekmonthplanner.utils;

import androidx.annotation.ColorRes;

public class DateItem {

    private final String dayOfMonth;
    private final String dayOfWeek;

    @ColorRes
    private final int colorInt;

    public DateItem(
            String dayOfMonth,
            String dayOfWeek,
            @ColorRes int colorInt
    ) {
        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
        this.colorInt = colorInt;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getColorInt() {
        return colorInt;
    }
}