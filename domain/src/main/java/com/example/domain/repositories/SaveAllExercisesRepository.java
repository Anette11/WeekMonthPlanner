package com.example.domain.repositories;

import com.example.domain.local.Exercise;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public interface SaveAllExercisesRepository {

    Completable saveAll(List<Exercise> list);
}