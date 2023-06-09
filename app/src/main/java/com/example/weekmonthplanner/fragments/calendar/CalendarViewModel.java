package com.example.weekmonthplanner.fragments.calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.domain.local.Exercise;
import com.example.domain.use_cases.GetAllExercisesUseCase;
import com.example.weekmonthplanner.R;
import com.example.weekmonthplanner.screen_items.ItemDivider;
import com.example.weekmonthplanner.screen_items.ItemExercise;
import com.example.weekmonthplanner.screen_items.ItemSpace;
import com.example.weekmonthplanner.screen_items.ItemTextLarge;
import com.example.weekmonthplanner.screen_items.ItemTextSmall;
import com.example.weekmonthplanner.screen_items.ItemWeek;
import com.example.weekmonthplanner.screen_items.ScreenItem;
import com.example.weekmonthplanner.utils.DateItem;
import com.example.weekmonthplanner.utils.ResourcesProvider;
import com.example.weekmonthplanner.utils.RxFragmentNotifier;
import com.example.weekmonthplanner.utils.WeekCreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

@HiltViewModel
public class CalendarViewModel extends ViewModel {

    private final WeekCreator weekCreator;
    private final ResourcesProvider resourcesProvider;
    private final GetAllExercisesUseCase getAllExercisesUseCase;
    private final RxFragmentNotifier rxFragmentNotifier;
    private Disposable disposableGetAllExercisesUseCase;

    @Inject
    public CalendarViewModel(
            WeekCreator weekCreator,
            ResourcesProvider resourcesProvider,
            GetAllExercisesUseCase getAllExercisesUseCase,
            RxFragmentNotifier rxFragmentNotifier
    ) {
        this.weekCreator = weekCreator;
        this.resourcesProvider = resourcesProvider;
        this.getAllExercisesUseCase = getAllExercisesUseCase;
        this.rxFragmentNotifier = rxFragmentNotifier;
        createScreenItems();
        observeRxFragmentNotifier();
    }

    private final MutableLiveData<List<ScreenItem>> _screenItems = new MutableLiveData<>();
    final LiveData<List<ScreenItem>> screenItems = _screenItems;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private void createScreenItems() {
        if (disposableGetAllExercisesUseCase != null) disposableGetAllExercisesUseCase.dispose();
        disposableGetAllExercisesUseCase = getAllExercisesUseCase.getAll()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        exercises -> {
                            if (!exercises.isEmpty()) _screenItems.postValue(fillScreen(exercises));
                        },
                        throwable -> Timber.e(throwable.getLocalizedMessage())
                );
        compositeDisposable.add(disposableGetAllExercisesUseCase);
    }

    private List<ScreenItem> fillScreen(
            List<Exercise> exercises
    ) {
        Collections.sort(exercises, new Comparator<Exercise>() {
            @Override
            public int compare(Exercise exercise1, Exercise exercise2) {
                return exercise1.id - exercise2.id;
            }
        });
        List<ScreenItem> list = new ArrayList<>();
        list.add(new ItemTextLarge(resourcesProvider.getString(R.string.calendar)));
        List<DateItem> dateItems = weekCreator.createDateItems();
        list.add(new ItemWeek(dateItems));
        list.add(new ItemDivider());

        ItemTextSmall itemTextSmallToday = new ItemTextSmall(resourcesProvider.getString(R.string.today));
        ItemTextSmall itemTextSmallCompleted = new ItemTextSmall(resourcesProvider.getString(R.string.completed));
        ItemTextSmall itemTextSmallOnTheWeek = new ItemTextSmall(resourcesProvider.getString(R.string.on_the_week));
        ItemTextSmall itemTextSmallNextWeek = new ItemTextSmall(resourcesProvider.getString(R.string.next_week));

        List<ScreenItem> exercisesToday = new ArrayList<>();
        List<ScreenItem> exercisesCompleted = new ArrayList<>();
        List<ScreenItem> exercisesOnTheWeek = new ArrayList<>();
        List<ScreenItem> exercisesNextWeek = new ArrayList<>();

        boolean areAllExercisesCompleted = weekCreator.areAllExercisesCompleted(exercises);

        for (int i = 0; i < exercises.size(); i++) {
            int colorInt = R.color.black_medium;
            if (weekCreator.isExerciseToday(exercises.get(i).id) && !exercises.get(i).isCompleted) {
                colorInt = R.color.green_light;
            }
            if (weekCreator.isExerciseToday(exercises.get(i).id) && !exercises.get(i).isCompleted) {
                exercisesToday.add(new ItemExercise(
                        weekCreator.createDateString(exercises.get(i).id, false),
                        exercises.get(i).name,
                        colorInt));
            }
            if (weekCreator.isExerciseToday(exercises.get(i).id) && exercises.get(i).isCompleted) {
                exercisesCompleted.add(new ItemExercise(
                        weekCreator.createDateString(exercises.get(i).id, false),
                        exercises.get(i).name,
                        colorInt));
            }
            if (weekCreator.isExerciseInPast(exercises.get(i).id)) {
                exercisesCompleted.add(new ItemExercise(
                        weekCreator.createDateString(exercises.get(i).id, false),
                        exercises.get(i).name,
                        colorInt));
            }
            if (weekCreator.isExerciseInFuture(exercises.get(i).id)) {
                exercisesOnTheWeek.add(new ItemExercise(
                        weekCreator.createDateString(exercises.get(i).id, false),
                        exercises.get(i).name,
                        colorInt));
            }
        }

        if (areAllExercisesCompleted) {
            for (int i = 0; i < exercises.size(); i++) {
                exercisesNextWeek.add(new ItemExercise(
                        weekCreator.createDateString(exercises.get(i).id, true),
                        exercises.get(i).name,
                        R.color.black_medium));
            }
        }

        int firstIndexInList = 0;

        Collections.reverse(exercisesToday);
        Collections.reverse(exercisesOnTheWeek);
        Collections.reverse(exercisesCompleted);
        Collections.reverse(exercisesNextWeek);

        if (!exercisesToday.isEmpty()) {
            exercisesToday.add(firstIndexInList, itemTextSmallToday);
        }
        if (!exercisesCompleted.isEmpty()) {
            exercisesCompleted.add(firstIndexInList, itemTextSmallCompleted);
        }
        if (!exercisesOnTheWeek.isEmpty()) {
            exercisesOnTheWeek.add(firstIndexInList, itemTextSmallOnTheWeek);
        }
        if (!exercisesNextWeek.isEmpty()) {
            exercisesNextWeek.add(firstIndexInList, itemTextSmallNextWeek);
        }

        list.addAll(exercisesToday);
        list.addAll(exercisesOnTheWeek);
        list.addAll(exercisesCompleted);
        list.addAll(exercisesNextWeek);

        list.add(new ItemSpace());

        return list;
    }

    private void observeRxFragmentNotifier() {
        Disposable disposable = rxFragmentNotifier.getPublishSubject()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        object -> createScreenItems(),
                        throwable -> Timber.e(throwable.getLocalizedMessage())
                );
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}