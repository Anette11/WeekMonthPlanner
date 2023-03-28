package com.example.weekmonthplanner.screen_items;

public class ItemExercise implements ScreenItem {

    private String date;
    private String exerciseNumber;

    public ItemExercise(String date, String exerciseNumber) {
        this.date = date;
        this.exerciseNumber = exerciseNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExerciseNumber() {
        return exerciseNumber;
    }

    public void setExerciseNumber(String exerciseNumber) {
        this.exerciseNumber = exerciseNumber;
    }
}
