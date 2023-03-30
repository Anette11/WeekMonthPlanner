package com.example.weekmonthplanner.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM exercise_table")
    Flowable<List<Exercise>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable saveAll(List<Exercise> list);
}