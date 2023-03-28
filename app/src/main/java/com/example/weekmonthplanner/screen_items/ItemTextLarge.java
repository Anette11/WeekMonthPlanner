package com.example.weekmonthplanner.screen_items;

public class ItemTextLarge implements ScreenItem {

    private String text;

    public ItemTextLarge(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}