package com.example.weekmonthplanner.screen_items;

import androidx.annotation.DrawableRes;

public class ItemGreeting implements ScreenItem {

    private final String greeting;
    private final String full_name;

    @DrawableRes
    private final int image;

    public ItemGreeting(
            String greeting,
            String full_name,
            int image
    ) {
        this.greeting = greeting;
        this.full_name = full_name;
        this.image = image;
    }

    public String getGreeting() {
        return greeting;
    }

    public String getFullName() {
        return full_name;
    }

    public int getImage() {
        return image;
    }
}