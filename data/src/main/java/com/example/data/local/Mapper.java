package com.example.data.local;

import com.example.domain.local.Exercise;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    private static Exercise exerciseDboToExercise(
            ExerciseDbo exerciseDbo
    ) {
        return new Exercise(
                exerciseDbo.id,
                exerciseDbo.isCompleted,
                exerciseDbo.name);
    }

    private static ExerciseDbo exerciseToExerciseDbo(
            Exercise exercise
    ) {
        return new ExerciseDbo(
                exercise.id,
                exercise.isCompleted,
                exercise.name);
    }

    public static List<Exercise> listExercisesDboToListExercises(
            List<ExerciseDbo> list
    ) {
        List<Exercise> exercises = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ExerciseDbo exerciseDbo = list.get(i);
            Exercise exercise = exerciseDboToExercise(exerciseDbo);
            exercises.add(exercise);
        }
        return exercises;
    }

    public static List<ExerciseDbo> listExercisesToListExercisesDbo(
            List<Exercise> list
    ) {
        List<ExerciseDbo> exercises = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Exercise exercise = list.get(i);
            ExerciseDbo exerciseDbo = exerciseToExerciseDbo(exercise);
            exercises.add(exerciseDbo);
        }
        return exercises;
    }
}