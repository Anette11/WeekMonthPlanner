package com.example.weekmonthplanner.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weekmonthplanner.adapters.viewholders.ViewHolderItemDivider;
import com.example.weekmonthplanner.adapters.viewholders.ViewHolderItemExercise;
import com.example.weekmonthplanner.adapters.viewholders.ViewHolderItemSpace;
import com.example.weekmonthplanner.adapters.viewholders.ViewHolderItemTextLarge;
import com.example.weekmonthplanner.adapters.viewholders.ViewHolderItemTextSmall;
import com.example.weekmonthplanner.adapters.viewholders.ViewHolderItemWeek;
import com.example.weekmonthplanner.databinding.ItemDividerBinding;
import com.example.weekmonthplanner.databinding.ItemExerciseBinding;
import com.example.weekmonthplanner.databinding.ItemSpaceBinding;
import com.example.weekmonthplanner.databinding.ItemTextLargeBinding;
import com.example.weekmonthplanner.databinding.ItemTextSmallBinding;
import com.example.weekmonthplanner.databinding.ItemWeekBinding;
import com.example.weekmonthplanner.screen_items.ItemDivider;
import com.example.weekmonthplanner.screen_items.ItemExercise;
import com.example.weekmonthplanner.screen_items.ItemSpace;
import com.example.weekmonthplanner.screen_items.ItemTextLarge;
import com.example.weekmonthplanner.screen_items.ItemTextSmall;
import com.example.weekmonthplanner.screen_items.ItemWeek;
import com.example.weekmonthplanner.screen_items.ScreenItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CalendarRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Inject
    public CalendarRecyclerViewAdapter() {
    }

    private final List<ScreenItem> list = new ArrayList<>();

    public void updateList(List<ScreenItem> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        if (viewType == RecyclerViewItem.ITEM_TEXT_LARGE.value) {
            ItemTextLargeBinding itemTextLargeBinding = ItemTextLargeBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolderItemTextLarge(itemTextLargeBinding);
        } else if (viewType == RecyclerViewItem.ITEM_WEEK.value) {
            ItemWeekBinding itemWeekBinding = ItemWeekBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolderItemWeek(itemWeekBinding);
        } else if (viewType == RecyclerViewItem.ITEM_DIVIDER.value) {
            ItemDividerBinding itemDividerBinding = ItemDividerBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolderItemDivider(itemDividerBinding);
        } else if (viewType == RecyclerViewItem.ITEM_TEXT_SMALL.value) {
            ItemTextSmallBinding itemTextSmallBinding = ItemTextSmallBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolderItemTextSmall(itemTextSmallBinding);
        } else if (viewType == RecyclerViewItem.ITEM_EXERCISE.value) {
            ItemExerciseBinding itemExerciseBinding = ItemExerciseBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolderItemExercise(itemExerciseBinding);
        } else if (viewType == RecyclerViewItem.ITEM_SPACE.value) {
            ItemSpaceBinding itemSpaceBinding = ItemSpaceBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolderItemSpace(itemSpaceBinding);
        }
        throw new RuntimeException("Can't get viewHolder");
    }

    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder,
            int position
    ) {
        ScreenItem screenItem = list.get(position);
        if (screenItem instanceof ItemTextLarge) {
            ((ViewHolderItemTextLarge) holder).bind((ItemTextLarge) screenItem);
        }
        if (screenItem instanceof ItemWeek) {
            ((ViewHolderItemWeek) holder).bind((ItemWeek) screenItem);
        }
        if (screenItem instanceof ItemTextSmall) {
            ((ViewHolderItemTextSmall) holder).bind((ItemTextSmall) screenItem);
        }
        if (screenItem instanceof ItemExercise) {
            ((ViewHolderItemExercise) holder).bind((ItemExercise) screenItem);
        }
    }

    @Override
    public int getItemViewType(int position) {
        ScreenItem screenItem = list.get(position);
        if (screenItem instanceof ItemTextLarge) {
            return RecyclerViewItem.ITEM_TEXT_LARGE.value;
        } else if (screenItem instanceof ItemWeek) {
            return RecyclerViewItem.ITEM_WEEK.value;
        } else if (screenItem instanceof ItemDivider) {
            return RecyclerViewItem.ITEM_DIVIDER.value;
        } else if (screenItem instanceof ItemTextSmall) {
            return RecyclerViewItem.ITEM_TEXT_SMALL.value;
        } else if (screenItem instanceof ItemExercise) {
            return RecyclerViewItem.ITEM_EXERCISE.value;
        } else if (screenItem instanceof ItemSpace) {
            return RecyclerViewItem.ITEM_SPACE.value;
        }
        throw new RuntimeException("Can't get item type");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}