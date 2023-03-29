package com.example.weekmonthplanner.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {Exercise.class},
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ExerciseDao exerciseDao();

    public static String name = "app_database_name";
}