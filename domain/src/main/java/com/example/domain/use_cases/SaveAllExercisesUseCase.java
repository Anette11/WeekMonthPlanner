package com.example.domain.use_cases;

import com.example.domain.local.Exercise;
import com.example.domain.repositories.SaveAllExercisesRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class SaveAllExercisesUseCase {

    private final SaveAllExercisesRepository saveAllExercisesRepository;

    public SaveAllExercisesUseCase(
            SaveAllExercisesRepository saveAllExercisesRepository
    ) {
        this.saveAllExercisesRepository = saveAllExercisesRepository;
    }

    public Completable saveAll(List<Exercise> list) {
        return saveAllExercisesRepository.saveAll(list);
    }
}