package com.example.domain.repositories;

import com.example.domain.local.Exercise;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface GetAllExercisesRepository {

    Flowable<List<Exercise>> getAll();
}