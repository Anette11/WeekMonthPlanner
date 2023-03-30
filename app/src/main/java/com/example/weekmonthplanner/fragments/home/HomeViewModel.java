package com.example.weekmonthplanner.fragments.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.domain.local.Exercise;
import com.example.domain.use_cases.GetAllExercisesUseCase;
import com.example.domain.use_cases.SaveAllExercisesUseCase;
import com.example.weekmonthplanner.R;
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
import timber.log.Timber;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final WeekCreator weekCreator;
    private final ResourcesProvider resourcesProvider;
    private final GetAllExercisesUseCase getAllExercisesUseCase;
    private final SaveAllExercisesUseCase saveAllExercisesUseCase;

    @Inject
    public HomeViewModel(
            WeekCreator weekCreator,
            ResourcesProvider resourcesProvider,
            GetAllExercisesUseCase getAllExercisesUseCase,
            SaveAllExercisesUseCase saveAllExercisesUseCase
    ) {
        this.weekCreator = weekCreator;
        this.resourcesProvider = resourcesProvider;
        this.getAllExercisesUseCase = getAllExercisesUseCase;
        this.saveAllExercisesUseCase = saveAllExercisesUseCase;
    }

    private final MutableLiveData<List<ScreenItem>> _screenItems = new MutableLiveData<>();
    final LiveData<List<ScreenItem>> screenItems = _screenItems;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void createScreenItems() {
        compositeDisposable.clear();
        Disposable disposable = getAllExercisesUseCase.getAll()
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
                                int exerciseIndex = weekCreator.getCurrentDayOfWeekIndex();
                                Exercise exerciseFound = null;
                                for (int i = 0; i < exercises.size(); i++) {
                                    Exercise exercise = exercises.get(i);
                                    if (exerciseIndex == exercise.id && !exercise.isCompleted) {
                                        exerciseFound = exercise;
                                    }
                                }
                                if (exerciseFound != null) {
                                    list.add(
                                            new ItemMainBlockMenu(
                                                    String.format(resourcesProvider.getString(R.string.exercise_completed), exerciseFound.name),
                                                    exerciseFound));
                                }
                                _screenItems.postValue(list);
                            }
                        },
                        throwable -> {
                            Timber.e(throwable.getLocalizedMessage());
                        }
                );
        compositeDisposable.add(disposable);
    }

    public void setOnExerciseCompleteClick(ItemMainBlockMenu screenItem) {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise(screenItem.getExercise().id, true, screenItem.getExercise().name));
        Disposable disposable = saveAllExercisesUseCase.saveAll(exercises)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        () -> {
                        },
                        throwable -> {
                            Timber.e(throwable.getLocalizedMessage());
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