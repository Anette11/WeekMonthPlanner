package com.example.weekmonthplanner.repositories;

import com.example.weekmonthplanner.data.Exercise;
import com.example.weekmonthplanner.data.ExerciseDao;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class HomeRepositoryImpl implements HomeRepository {

    private final ExerciseDao exerciseDao;

    public HomeRepositoryImpl(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    @Override
    public Flowable<List<Exercise>> getAll() {
        return exerciseDao.getAll();
    }
}