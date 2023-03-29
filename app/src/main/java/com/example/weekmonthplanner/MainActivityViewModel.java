package com.example.weekmonthplanner;

import androidx.lifecycle.ViewModel;

import com.example.weekmonthplanner.data.Exercise;
import com.example.weekmonthplanner.repositories.MainRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
        list.add(new Exercise(1, false, "Exercise1 completed"));
        list.add(new Exercise(2, false, "Exercise2 completed"));
        list.add(new Exercise(3, false, "Exercise3 completed"));
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