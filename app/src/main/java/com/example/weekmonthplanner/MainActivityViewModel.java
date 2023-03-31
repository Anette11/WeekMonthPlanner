package com.example.weekmonthplanner;

import androidx.lifecycle.ViewModel;

import com.example.domain.local.Exercise;
import com.example.domain.use_cases.GetAllExercisesUseCase;
import com.example.domain.use_cases.SaveAllExercisesUseCase;
import com.example.weekmonthplanner.utils.ResourcesProvider;
import com.example.weekmonthplanner.utils.WeekCreator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

@HiltViewModel
public class MainActivityViewModel extends ViewModel {

    private final GetAllExercisesUseCase getAllExercisesUseCase;
    private final SaveAllExercisesUseCase saveAllExercisesUseCase;
    private final WeekCreator weekCreator;
    private final ResourcesProvider resourcesProvider;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public MainActivityViewModel(
            GetAllExercisesUseCase getAllExercisesUseCase,
            SaveAllExercisesUseCase saveAllExercisesUseCase,
            WeekCreator weekCreator,
            ResourcesProvider resourcesProvider
    ) {
        this.getAllExercisesUseCase = getAllExercisesUseCase;
        this.saveAllExercisesUseCase = saveAllExercisesUseCase;
        this.weekCreator = weekCreator;
        this.resourcesProvider = resourcesProvider;
        getExercises();
    }

    private void getExercises() {
        compositeDisposable.clear();
        List<Exercise> list = createExercisesAccordingToTimetable();
        Disposable disposableGetAll = getAllExercisesUseCase.getAll()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        exercises -> {
                            List<Exercise> listToDeReSaved;
                            if (exercises.isEmpty()) {
                                listToDeReSaved = list;
                            } else {
                                listToDeReSaved = weekCreator.updateInExercisesFieldIsCompleted(exercises);
                            }
                            if (!listToDeReSaved.isEmpty()) {
                                Disposable disposableSaveAll = saveAllExercisesUseCase
                                        .saveAll(listToDeReSaved)
                                        .subscribe();
                                compositeDisposable.add(disposableSaveAll);
                            }
                        },
                        throwable -> Timber.e(throwable.getLocalizedMessage())
                );
        compositeDisposable.add(disposableGetAll);
    }

    private List<Exercise> createExercisesAccordingToTimetable() {
        List<Exercise> list = new ArrayList<>();
        boolean isCompleted = false;
        long modifiedAt = new Date().getTime();
        list.add(new Exercise(
                Calendar.MONDAY,
                isCompleted,
                resourcesProvider.getString(R.string.exercise_block_monday_name),
                modifiedAt));
        list.add(new Exercise(
                Calendar.WEDNESDAY,
                isCompleted,
                resourcesProvider.getString(R.string.exercise_block_wednesday_name),
                modifiedAt));
        list.add(new Exercise(
                Calendar.FRIDAY,
                isCompleted,
                resourcesProvider.getString(R.string.exercise_block_friday_name),
                modifiedAt));
        return list;
    }

    public void onTimeChanged() {
        getExercises();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}