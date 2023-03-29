package com.example.weekmonthplanner.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_table")
public class Exercise {

    @PrimaryKey
    public int id;

    public boolean isCompleted;
    public String name;

    public Exercise(
            int id,
            boolean isCompleted,
            String name
    ) {
        this.id = id;
        this.isCompleted = isCompleted;
        this.name = name;
    }
}