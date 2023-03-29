package com.example.weekmonthplanner.repositories;

import com.example.weekmonthplanner.data.Exercise;
import com.example.weekmonthplanner.data.ExerciseDao;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MainRepositoryImpl implements MainRepository {

    private final ExerciseDao exerciseDao;

    public MainRepositoryImpl(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    @Override
    public Flowable<List<Exercise>> getAll() {
        return exerciseDao.getAll();
    }

    @Override
    public Completable saveAll(List<Exercise> list) {
        return exerciseDao.saveAll(list);
    }
}