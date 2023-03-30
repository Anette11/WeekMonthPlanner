package com.example.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {ExerciseDbo.class},
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ExerciseDao exerciseDao();

    public static String name = "app_database_name";
}