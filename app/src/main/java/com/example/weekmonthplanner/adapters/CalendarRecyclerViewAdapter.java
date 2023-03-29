package com.example.weekmonthplanner.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weekmonthplanner.databinding.ItemDateBinding;
import com.example.weekmonthplanner.databinding.ItemDividerBinding;
import com.example.weekmonthplanner.databinding.ItemExerciseBinding;
import com.example.weekmonthplanner.databinding.ItemTextLargeBinding;
import com.example.weekmonthplanner.databinding.ItemTextSmallBinding;
import com.example.weekmonthplanner.databinding.ItemWeekBinding;
import com.example.weekmonthplanner.screen_items.ItemDivider;
import com.example.weekmonthplanner.screen_items.ItemExercise;
import com.example.weekmonthplanner.screen_items.ItemTextLarge;
import com.example.weekmonthplanner.screen_items.ItemTextSmall;
import com.example.weekmonthplanner.screen_items.ItemWeek;
import com.example.weekmonthplanner.screen_items.ScreenItem;
import com.example.weekmonthplanner.utils.DateItem;

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
            return new CalendarRecyclerViewAdapter.ViewHolderItemTextLarge(itemTextLargeBinding);
        } else if (viewType == RecyclerViewItem.ITEM_WEEK.value) {
            ItemWeekBinding itemWeekBinding = ItemWeekBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new CalendarRecyclerViewAdapter.ViewHolderItemWeek(itemWeekBinding);
        } else if (viewType == RecyclerViewItem.ITEM_DIVIDER.value) {
            ItemDividerBinding itemDividerBinding = ItemDividerBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new CalendarRecyclerViewAdapter.ViewHolderItemDivider(itemDividerBinding);
        } else if (viewType == RecyclerViewItem.ITEM_TEXT_SMALL.value) {
            ItemTextSmallBinding itemTextSmallBinding = ItemTextSmallBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new CalendarRecyclerViewAdapter.ViewHolderItemTextSmall(itemTextSmallBinding);
        } else if (viewType == RecyclerViewItem.ITEM_EXERCISE.value) {
            ItemExerciseBinding itemExerciseBinding = ItemExerciseBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new CalendarRecyclerViewAdapter.ViewHolderItemExercise(itemExerciseBinding);
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
            ((CalendarRecyclerViewAdapter.ViewHolderItemTextLarge) holder).bind((ItemTextLarge) screenItem);
        }
        if (screenItem instanceof ItemWeek) {
            ((CalendarRecyclerViewAdapter.ViewHolderItemWeek) holder).bind((ItemWeek) screenItem);
        }
        if (screenItem instanceof ItemDivider) {
            ((CalendarRecyclerViewAdapter.ViewHolderItemDivider) holder).bind((ItemDivider) screenItem);
        }
        if (screenItem instanceof ItemTextSmall) {
            ((CalendarRecyclerViewAdapter.ViewHolderItemTextSmall) holder).bind((ItemTextSmall) screenItem);
        }
        if (screenItem instanceof ItemExercise) {
            ((CalendarRecyclerViewAdapter.ViewHolderItemExercise) holder).bind((ItemExercise) screenItem);
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
        }
        throw new RuntimeException("Can't get item type");
    }

    @Override
    public int getItemCount() {
        return list.size();
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

    private static class ViewHolderItemTextLarge extends RecyclerView.ViewHolder {

        private final ItemTextLargeBinding binding;

        public ViewHolderItemTextLarge(ItemTextLargeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ItemTextLarge screenItem) {
            binding.textView.setText(screenItem.getText());
        }
    }

    private static class ViewHolderItemTextSmall extends RecyclerView.ViewHolder {

        private final ItemTextSmallBinding binding;

        public ViewHolderItemTextSmall(ItemTextSmallBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ItemTextSmall screenItem) {
            binding.textView.setText(screenItem.getText());
        }
    }

    private static class ViewHolderItemExercise extends RecyclerView.ViewHolder {

        private final ItemExerciseBinding binding;

        public ViewHolderItemExercise(ItemExerciseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ItemExercise screenItem) {
            binding.textViewDate.setText(screenItem.getDate());
            binding.textViewExerciseNumber.setText(screenItem.getExerciseNumber());
        }
    }

    private static class ViewHolderItemDivider extends RecyclerView.ViewHolder {

        private final ItemDividerBinding binding;

        public ViewHolderItemDivider(ItemDividerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ItemDivider screenItem) {

        }
    }
}