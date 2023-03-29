package com.example.weekmonthplanner.screen_items;

import androidx.annotation.ColorRes;

public class ItemExercise implements ScreenItem {

    private final String date;
    private final String exerciseNumber;
    @ColorRes
    private final int colorInt;

    public ItemExercise(
            String date,
            String exerciseNumber,
            @ColorRes int colorInt
    ) {
        this.date = date;
        this.exerciseNumber = exerciseNumber;
        this.colorInt = colorInt;
    }

    public String getDate() {
        return date;
    }

    public String getExerciseNumber() {
        return exerciseNumber;
    }

    public int getColorInt() {
        return colorInt;
    }
}