package com.example.weekmonthplanner.screen_items;

import com.example.weekmonthplanner.data.Exercise;

public class ItemMainBlockMenu implements ScreenItem {

    private final String buttonText;
    private final Exercise exercise;

    public ItemMainBlockMenu(
            String buttonText,
            Exercise exercise
    ) {
        this.buttonText = buttonText;
        this.exercise = exercise;
    }

    public String getButtonText() {
        return buttonText;
    }

    public Exercise getExercise() {
        return exercise;
    }
}