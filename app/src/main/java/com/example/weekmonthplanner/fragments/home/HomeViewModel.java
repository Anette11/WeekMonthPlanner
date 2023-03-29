package com.example.weekmonthplanner.fragments.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weekmonthplanner.R;
import com.example.weekmonthplanner.data.Exercise;
import com.example.weekmonthplanner.data.ExerciseDao;
import com.example.weekmonthplanner.screen_items.ItemGreeting;
import com.example.weekmonthplanner.screen_items.ItemMainBlockMenu;
import com.example.weekmonthplanner.screen_items.ItemWeek;
import com.example.weekmonthplanner.screen_items.ScreenItem;
import com.example.weekmonthplanner.utils.DateItem;
import com.example.weekmonthplanner.utils.ResourcesProvider;
import com.example.weekmonthplanner.utils.WeekCreator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final WeekCreator weekCreator;

    private final ResourcesProvider resourcesProvider;
    private final ExerciseDao exerciseDao;

    @Inject
    public HomeViewModel(
            WeekCreator weekCreator,
            ResourcesProvider resourcesProvider,
            ExerciseDao exerciseDao
    ) {
        this.weekCreator = weekCreator;
        this.resourcesProvider = resourcesProvider;
        this.exerciseDao = exerciseDao;
        createScreenItems();
    }

    private final MutableLiveData<List<ScreenItem>> _screenItems = new MutableLiveData<>();
    final LiveData<List<ScreenItem>> screenItems = _screenItems;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private void createScreenItems() {
        Disposable disposable = exerciseDao.getAll()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        exercises -> {
                            if (!exercises.isEmpty()) {
                                List<ScreenItem> list = new ArrayList<>();
                                list.add(
                                        new ItemGreeting(
                                                resourcesProvider.getString(R.string.greeting),
                                                resourcesProvider.getString(R.string.full_name),
                                                R.drawable.abb70d9a47429527d540bd9a3d7aae8f));
                                List<DateItem> dateItems = weekCreator.createDateItems();
                                list.add(new ItemWeek(dateItems));
                                int exerciseIndex = weekCreator.getExerciseIndex();
                                Exercise exerciseFound = null;
                                for (int i = 0; i < exercises.size(); i++) {
                                    Exercise exercise = exercises.get(i);
                                    if (exerciseIndex == exercise.id) {
                                        exerciseFound = exercise;
                                    }
                                }
                                if (exerciseFound != null) {
                                    list.add(
                                            new ItemMainBlockMenu(
                                                    String.format(resourcesProvider.getString(R.string.exercise_completed),
                                                            exerciseFound.name)));
                                }
                                _screenItems.postValue(list);
                            }
                        },
                        throwable -> {
                        }
                );
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}