package com.example.weekmonthplanner.utils;

public class DateItem {

    private final String dayOfMonth;
    private final String dayOfWeek;

    public DateItem(String dayOfMonth, String dayOfWeek) {
        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }
}