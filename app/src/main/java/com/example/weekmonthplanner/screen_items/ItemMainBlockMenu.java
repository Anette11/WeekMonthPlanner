package com.example.weekmonthplanner.screen_items;

public class ItemMainBlockMenu implements ScreenItem {

    private String buttonText;

    public ItemMainBlockMenu(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }
}