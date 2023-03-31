package com.example.weekmonthplanner.adapters;

public enum RecyclerViewItem {

    ITEM_DIVIDER(0),
    ITEM_EXERCISE(1),
    ITEM_GREETING(2),
    ITEM_MAIN_BLOCK_MENU(3),
    ITEM_TEXT_LARGE(4),
    ITEM_TEXT_SMALL(5),
    ITEM_WEEK(6),
    ITEM_SPACE(7);

    public final int value;

    RecyclerViewItem(int value) {
        this.value = value;
    }
}