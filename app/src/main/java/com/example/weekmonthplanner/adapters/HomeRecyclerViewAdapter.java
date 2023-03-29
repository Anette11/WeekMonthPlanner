package com.example.weekmonthplanner.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weekmonthplanner.databinding.ItemDateBinding;
import com.example.weekmonthplanner.databinding.ItemGreetingBinding;
import com.example.weekmonthplanner.databinding.ItemMainBlockMenuBinding;
import com.example.weekmonthplanner.databinding.ItemWeekBinding;
import com.example.weekmonthplanner.screen_items.ItemGreeting;
import com.example.weekmonthplanner.screen_items.ItemMainBlockMenu;
import com.example.weekmonthplanner.screen_items.ItemWeek;
import com.example.weekmonthplanner.screen_items.ScreenItem;
import com.example.weekmonthplanner.utils.DateItem;
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
            return new ViewHolderItemMainBlockMenu(itemMainBlockMenuBinding);
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

    private static class ViewHolderItemGreeting extends RecyclerView.ViewHolder {

        private final ItemGreetingBinding binding;

        public ViewHolderItemGreeting(ItemGreetingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ItemGreeting screenItem) {
            binding.textViewGreeting.setText(screenItem.getGreeting());
            binding.textViewFullName.setText(screenItem.getFullName());
            binding.imageView.setImageResource(screenItem.getImage());
        }
    }

    private static class ViewHolderItemWeek extends RecyclerView.ViewHolder {

        private final ItemWeekBinding binding;

        public ViewHolderItemWeek(ItemWeekBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ItemWeek screenItem) {
            List<DateItem> dateItems = screenItem.getList();
            if (dateItems.size() != 7) return;
            fillDateItem(binding.dateMonday, dateItems.get(0));
            fillDateItem(binding.dateTuesday, dateItems.get(1));
            fillDateItem(binding.dateWednesday, dateItems.get(2));
            fillDateItem(binding.dateThursday, dateItems.get(3));
            fillDateItem(binding.dateFriday, dateItems.get(4));
            fillDateItem(binding.dateSaturday, dateItems.get(5));
            fillDateItem(binding.dateSunday, dateItems.get(6));
        }

        private void fillDateItem(
                ItemDateBinding binding,
                DateItem dateItem
        ) {
            binding.textViewDayOfMonth.setText(dateItem.getDayOfMonth());
            binding.textViewDayOfWeek.setText(dateItem.getDayOfWeek());
            binding.cardView.setCardBackgroundColor(
                    ContextCompat.getColor(binding.getRoot().getContext(),
                            dateItem.getColorInt()));
        }
    }

    private class ViewHolderItemMainBlockMenu extends RecyclerView.ViewHolder {

        private final ItemMainBlockMenuBinding binding;

        public ViewHolderItemMainBlockMenu(
                ItemMainBlockMenuBinding binding
        ) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ItemMainBlockMenu screenItem) {
            binding.textViewButtonExerciseCompleted.setText(screenItem.getButtonText());
            binding.cardViewExerciseCompleted.setOnClickListener(v -> {
                if (onExerciseCompleteClick != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onExerciseCompleteClick.onClick(screenItem);
                    }
                }
            });
        }
    }
}