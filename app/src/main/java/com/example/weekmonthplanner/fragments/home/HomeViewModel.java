package com.example.weekmonthplanner.fragments.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final WeekCreator weekCreator;

    private final ResourcesProvider resourcesProvider;

    @Inject
    public HomeViewModel(
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
        list.add(
                new ItemGreeting(
                        resourcesProvider.getString(R.string.greeting),
                        resourcesProvider.getString(R.string.full_name),
                        R.drawable.abb70d9a47429527d540bd9a3d7aae8f));
        List<DateItem> dateItems = weekCreator.createDateItems();
        list.add(new ItemWeek(dateItems));
        list.add(new ItemMainBlockMenu("Exercise1 completed"));
        _screenItems.setValue(list);
    }
}