package com.example.weekmonthplanner.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM exercise_table")
    List<Exercise> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(Exercise... exercises);
}