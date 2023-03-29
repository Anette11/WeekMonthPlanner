package com.example.weekmonthplanner.fragments.calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weekmonthplanner.R;
import com.example.weekmonthplanner.screen_items.ItemDivider;
import com.example.weekmonthplanner.screen_items.ItemExercise;
import com.example.weekmonthplanner.screen_items.ItemTextLarge;
import com.example.weekmonthplanner.screen_items.ItemTextSmall;
import com.example.weekmonthplanner.screen_items.ItemWeek;
import com.example.weekmonthplanner.screen_items.ScreenItem;
import com.example.weekmonthplanner.utils.DateItem;
import com.example.weekmonthplanner.utils.ResourcesProvider;
import com.example.weekmonthplanner.utils.WeekCreator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CalendarViewModel extends ViewModel {

    private final WeekCreator weekCreator;

    private final ResourcesProvider resourcesProvider;

    @Inject
    public CalendarViewModel(
            WeekCreator weekCreator,
            ResourcesProvider resourcesProvider
    ) {
        this.weekCreator = weekCreator;
        this.resourcesProvider = resourcesProvider;
        createScreenItems();
    }

    private final MutableLiveData<List<ScreenItem>> _screenItems = new MutableLiveData<>();
    final LiveData<List<ScreenItem>> screenItems = _screenItems;

    private void createScreenItems() {
        List<ScreenItem> list = new ArrayList<>();
        list.add(new ItemTextLarge(resourcesProvider.getString(R.string.calendar)));
        List<DateItem> dateItems = weekCreator.createDateItems();
        list.add(new ItemWeek(dateItems));
        list.add(new ItemDivider());
        list.add(new ItemTextSmall(resourcesProvider.getString(R.string.today)));
        list.add(new ItemExercise(
                resourcesProvider.getString(R.string.example_date),
                resourcesProvider.getString(R.string.exercise_1),
                R.color.green_light));
        list.add(new ItemTextSmall(resourcesProvider.getString(R.string.on_the_week)));
        list.add(new ItemExercise(
                resourcesProvider.getString(R.string.example_date),
                resourcesProvider.getString(R.string.exercise_1),
                R.color.black_medium));
        list.add(new ItemExercise(
                resourcesProvider.getString(R.string.example_date),
                resourcesProvider.getString(R.string.exercise_1),
                R.color.black_medium));
        _screenItems.setValue(list);
    }
}