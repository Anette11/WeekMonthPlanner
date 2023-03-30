package com.example.weekmonthplanner.repositories;

import com.example.weekmonthplanner.data.Exercise;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface CalendarRepository {

    Flowable<List<Exercise>> getAll();
}