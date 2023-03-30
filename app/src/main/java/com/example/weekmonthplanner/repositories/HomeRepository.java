package com.example.weekmonthplanner.repositories;

import com.example.weekmonthplanner.data.Exercise;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface HomeRepository {

    Flowable<List<Exercise>> getAll();

    Completable saveAll(List<Exercise> list);
}