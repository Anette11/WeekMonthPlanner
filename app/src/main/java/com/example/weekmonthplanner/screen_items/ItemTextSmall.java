package com.example.weekmonthplanner.screen_items;

public class ItemTextSmall implements ScreenItem {

    private String text;

    public ItemTextSmall(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}