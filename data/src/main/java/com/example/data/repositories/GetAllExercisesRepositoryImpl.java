package com.example.data.repositories;

import com.example.data.local.ExerciseDao;
import com.example.data.local.Mapper;
import com.example.domain.local.Exercise;
import com.example.domain.repositories.GetAllExercisesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class GetAllExercisesRepositoryImpl implements GetAllExercisesRepository {

    private final ExerciseDao exerciseDao;

    @Inject
    public GetAllExercisesRepositoryImpl(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    @Override
    public Flowable<List<Exercise>> getAll() {
        return exerciseDao.getAll().map(Mapper::listExercisesDboToListExercises);
    }
}