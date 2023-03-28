package com.example.weekmonthplanner.screen_items;

public class ItemMainBlockMenu implements ScreenItem {

    private final String buttonText;

    public ItemMainBlockMenu(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getButtonText() {
        return buttonText;
    }
}