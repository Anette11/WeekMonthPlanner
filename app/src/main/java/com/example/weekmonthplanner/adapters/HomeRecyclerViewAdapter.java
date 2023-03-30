package com.example.weekmonthplanner.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weekmonthplanner.adapters.viewholders.ViewHolderItemGreeting;
import com.example.weekmonthplanner.adapters.viewholders.ViewHolderItemMainBlockMenu;
import com.example.weekmonthplanner.adapters.viewholders.ViewHolderItemWeek;
import com.example.weekmonthplanner.databinding.ItemGreetingBinding;
import com.example.weekmonthplanner.databinding.ItemMainBlockMenuBinding;
import com.example.weekmonthplanner.databinding.ItemWeekBinding;
import com.example.weekmonthplanner.screen_items.ItemGreeting;
import com.example.weekmonthplanner.screen_items.ItemMainBlockMenu;
import com.example.weekmonthplanner.screen_items.ItemWeek;
import com.example.weekmonthplanner.screen_items.ScreenItem;
import com.example.weekmonthplanner.utils.OnExerciseCompleteClick;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Inject
    public HomeRecyclerViewAdapter() {
    }

    private OnExerciseCompleteClick onExerciseCompleteClick;

    public void setOnExerciseCompleteClick(
            OnExerciseCompleteClick onExerciseCompleteClick
    ) {
        this.onExerciseCompleteClick = onExerciseCompleteClick;
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
        if (viewType == RecyclerViewItem.ITEM_GREETING.value) {
            ItemGreetingBinding viewItemGreeting = ItemGreetingBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolderItemGreeting(viewItemGreeting);
        } else if (viewType == RecyclerViewItem.ITEM_WEEK.value) {
            ItemWeekBinding itemWeekBinding = ItemWeekBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolderItemWeek(itemWeekBinding);
        } else if (viewType == RecyclerViewItem.ITEM_MAIN_BLOCK_MENU.value) {
            ItemMainBlockMenuBinding itemMainBlockMenuBinding = ItemMainBlockMenuBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolderItemMainBlockMenu(itemMainBlockMenuBinding, onExerciseCompleteClick);
        }
        throw new RuntimeException("Can't get viewHolder");
    }

    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder,
            int position
    ) {
        ScreenItem screenItem = list.get(position);
        if (screenItem instanceof ItemGreeting) {
            ((ViewHolderItemGreeting) holder).bind((ItemGreeting) screenItem);
        }
        if (screenItem instanceof ItemWeek) {
            ((ViewHolderItemWeek) holder).bind((ItemWeek) screenItem);
        }
        if (screenItem instanceof ItemMainBlockMenu) {
            ((ViewHolderItemMainBlockMenu) holder).bind((ItemMainBlockMenu) screenItem);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        ScreenItem screenItem = list.get(position);
        if (screenItem instanceof ItemGreeting) {
            return RecyclerViewItem.ITEM_GREETING.value;
        } else if (screenItem instanceof ItemWeek) {
            return RecyclerViewItem.ITEM_WEEK.value;
        } else if (screenItem instanceof ItemMainBlockMenu) {
            return RecyclerViewItem.ITEM_MAIN_BLOCK_MENU.value;
        }
        throw new RuntimeException("Can't get item type");
    }
}