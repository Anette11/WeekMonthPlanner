package com.example.weekmonthplanner.screen_items;

import java.util.List;

public class ItemWeek implements ScreenItem {

    private List<DateItem> list;

    public ItemWeek(List<DateItem> list) {
        this.list = list;
    }

    public List<DateItem> getList() {
        return list;
    }

    public void setList(List<DateItem> list) {
        this.list = list;
    }
}