package com.example.weekmonthplanner.screen_items;

import com.example.weekmonthplanner.utils.DateItem;

import java.util.List;

public class ItemWeek implements ScreenItem {

    private final List<DateItem> list;

    public ItemWeek(List<DateItem> list) {
        this.list = list;
    }

    public List<DateItem> getList() {
        return list;
    }
}