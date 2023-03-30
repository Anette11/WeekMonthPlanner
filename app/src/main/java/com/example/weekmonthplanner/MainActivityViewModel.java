package com.example.weekmonthplanner;

import androidx.lifecycle.ViewModel;

import com.example.domain.local.Exercise;
import com.example.domain.use_cases.GetAllExercisesUseCase;
import com.example.domain.use_cases.SaveAllExercisesUseCase;

import java.util.ArrayList;
import java.util.Calendar;
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
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public MainActivityViewModel(
            GetAllExercisesUseCase getAllExercisesUseCase,
            SaveAllExercisesUseCase saveAllExercisesUseCase
    ) {
        this.getAllExercisesUseCase = getAllExercisesUseCase;
        this.saveAllExercisesUseCase = saveAllExercisesUseCase;
        getExercises();
    }

    private void getExercises() {
        List<Exercise> list = new ArrayList<>();
        list.add(new Exercise(Calendar.MONDAY, false, "Exercise1"));
        list.add(new Exercise(Calendar.WEDNESDAY, false, "Exercise2"));
        list.add(new Exercise(Calendar.FRIDAY, false, "Exercise3"));
        Disposable disposableGetAll = getAllExercisesUseCase.getAll()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        exercises -> {
                            if (exercises.isEmpty()) {
                                Disposable disposableSaveAll = saveAllExercisesUseCase.saveAll(list)
                                        .subscribe();
                                compositeDisposable.add(disposableSaveAll);
                            }
                        },
                        throwable -> {
                            Timber.e(throwable.getLocalizedMessage());
                        }
                );
        compositeDisposable.add(disposableGetAll);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}