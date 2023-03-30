package com.example.weekmonthplanner;

import androidx.lifecycle.ViewModel;

import com.example.weekmonthplanner.data.Exercise;
import com.example.weekmonthplanner.repositories.MainRepository;

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

    private final MainRepository mainRepository;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public MainActivityViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
        getExercises();
    }

    private void getExercises() {
        List<Exercise> list = new ArrayList<>();
        list.add(new Exercise(Calendar.MONDAY, false, "Exercise1"));
        list.add(new Exercise(Calendar.WEDNESDAY, false, "Exercise2"));
        list.add(new Exercise(Calendar.FRIDAY, false, "Exercise3"));
        Disposable disposableGetAll = mainRepository.getAll()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        exercises -> {
                            if (exercises.isEmpty()) {
                                Disposable disposableSaveAll = mainRepository.saveAll(list)
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