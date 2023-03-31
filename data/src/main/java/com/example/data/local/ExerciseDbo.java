package com.example.data.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "exercise_table")
public class ExerciseDbo {

    @PrimaryKey
    public int id;

    public boolean isCompleted;
    public String name;
    public long modifiedAt;

    public ExerciseDbo(
            int id,
            boolean isCompleted,
            String name
    ) {
        this.id = id;
        this.isCompleted = isCompleted;
        this.name = name;
        this.modifiedAt = new Date().getTime();
    }
}