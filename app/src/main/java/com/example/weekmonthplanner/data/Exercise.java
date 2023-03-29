package com.example.weekmonthplanner.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_table")
public class Exercise {

    @PrimaryKey(autoGenerate = false)
    public int id;

    public boolean isCompleted;
    public String name;
}