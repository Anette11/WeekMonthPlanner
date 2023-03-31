package com.example.weekmonthplanner;

import androidx.lifecycle.ViewModel;

import com.example.domain.local.Exercise;
import com.example.domain.use_cases.GetAllExercisesUseCase;
import com.example.domain.use_cases.SaveAllExercisesUseCase;
import com.example.weekmonthplanner.utils.ResourcesProvider;
import com.example.weekmonthplanner.utils.RxFragmentNotifier;
import com.example.weekmonthplanner.utils.WeekCreator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private final RxFragmentNotifier rxFragmentNotifier;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public MainActivityViewModel(
            GetAllExercisesUseCase getAllExercisesUseCase,
            SaveAllExercisesUseCase saveAllExercisesUseCase,
            WeekCreator weekCreator,
            RxFragmentNotifier rxFragmentNotifier
    ) {
        this.getAllExercisesUseCase = getAllExercisesUseCase;
        this.saveAllExercisesUseCase = saveAllExercisesUseCase;
        this.weekCreator = weekCreator;
        this.rxFragmentNotifier = rxFragmentNotifier;
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
        Map<Integer, String> map = weekCreator.getExercisesPlan();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            list.add(new Exercise(
                    entry.getKey(),
                    isCompleted,
                    entry.getValue(),
                    modifiedAt));
        }
        return list;
    }

    public void onTimeChanged() {
        getExercises();
        rxFragmentNotifier.onNext();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}