package com.example.weekmonthplanner.screen_items;

import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;

public class ItemGreeting implements ScreenItem {

    private String greeting;
    private String full_name;

    @DrawableRes
    private int image;

    public ItemGreeting(String greeting, String full_name, int image) {
        this.greeting = greeting;
        this.full_name = full_name;
        this.image = image;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}