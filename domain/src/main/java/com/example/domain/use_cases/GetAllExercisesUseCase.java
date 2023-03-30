package com.example.domain.use_cases;

import com.example.domain.local.Exercise;
import com.example.domain.repositories.GetAllExercisesRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class GetAllExercisesUseCase {

    private final GetAllExercisesRepository getAllExercisesRepository;

    public GetAllExercisesUseCase(
            GetAllExercisesRepository getAllExercisesRepository
    ) {
        this.getAllExercisesRepository = getAllExercisesRepository;
    }

    public Flowable<List<Exercise>> getAll() {
        return getAllExercisesRepository.getAll();
    }
}