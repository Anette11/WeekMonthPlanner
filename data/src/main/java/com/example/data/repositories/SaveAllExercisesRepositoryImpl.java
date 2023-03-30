package com.example.data.repositories;

import com.example.data.local.ExerciseDao;
import com.example.data.local.ExerciseDbo;
import com.example.data.local.Mapper;
import com.example.domain.local.Exercise;
import com.example.domain.repositories.SaveAllExercisesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;

public class SaveAllExercisesRepositoryImpl implements SaveAllExercisesRepository {

    private final ExerciseDao exerciseDao;

    @Inject
    public SaveAllExercisesRepositoryImpl(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    @Override
    public Completable saveAll(List<Exercise> list) {
        List<ExerciseDbo> exercises = Mapper.listExercisesToListExercisesDbo(list);
        return exerciseDao.saveAll(exercises);
    }
}