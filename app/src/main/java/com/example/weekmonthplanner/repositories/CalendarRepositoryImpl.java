package com.example.weekmonthplanner.repositories;

import com.example.weekmonthplanner.data.Exercise;
import com.example.weekmonthplanner.data.ExerciseDao;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class CalendarRepositoryImpl implements CalendarRepository {

    private final ExerciseDao exerciseDao;

    public CalendarRepositoryImpl(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    @Override
    public Flowable<List<Exercise>> getAll() {
        return exerciseDao.getAll();
    }
}