package com.example.weekmonthplanner.screen_items;

public class ItemExercise implements ScreenItem {

    private final String date;
    private final String exerciseNumber;

    public ItemExercise(
            String date,
            String exerciseNumber
    ) {
        this.date = date;
        this.exerciseNumber = exerciseNumber;
    }

    public String getDate() {
        return date;
    }

    public String getExerciseNumber() {
        return exerciseNumber;
    }
}