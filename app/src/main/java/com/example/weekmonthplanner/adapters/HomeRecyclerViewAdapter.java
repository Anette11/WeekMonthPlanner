package com.example.weekmonthplanner.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weekmonthplanner.databinding.ItemGreetingBinding;
import com.example.weekmonthplanner.databinding.ItemMainBlockMenuBinding;
import com.example.weekmonthplanner.databinding.ItemWeekBinding;
import com.example.weekmonthplanner.utils.DateItem;
import com.example.weekmonthplanner.screen_items.ItemGreeting;
import com.example.weekmonthplanner.screen_items.ItemMainBlockMenu;
import com.example.weekmonthplanner.screen_items.ItemWeek;
import com.example.weekmonthplanner.screen_items.ScreenItem;

import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<ScreenItem> list;

    public HomeRecyclerViewAdapter(List<ScreenItem> list) {
        this.list = list;
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
            binding.dateMonday.textViewDayOfMonth.setText(dateItems.get(0).getDayOfMonth());
            binding.dateMonday.textViewDayOfWeek.setText(dateItems.get(0).getDayOfWeek());
            binding.dateTuesday.textViewDayOfMonth.setText(dateItems.get(1).getDayOfMonth());
            binding.dateTuesday.textViewDayOfWeek.setText(dateItems.get(1).getDayOfWeek());
            binding.dateWednesday.textViewDayOfMonth.setText(dateItems.get(2).getDayOfMonth());
            binding.dateWednesday.textViewDayOfWeek.setText(dateItems.get(2).getDayOfWeek());
            binding.dateThursday.textViewDayOfMonth.setText(dateItems.get(3).getDayOfMonth());
            binding.dateThursday.textViewDayOfWeek.setText(dateItems.get(3).getDayOfWeek());
            binding.dateFriday.textViewDayOfMonth.setText(dateItems.get(4).getDayOfMonth());
            binding.dateFriday.textViewDayOfWeek.setText(dateItems.get(4).getDayOfWeek());
            binding.dateSaturday.textViewDayOfMonth.setText(dateItems.get(5).getDayOfMonth());
            binding.dateSaturday.textViewDayOfWeek.setText(dateItems.get(5).getDayOfWeek());
            binding.dateSunday.textViewDayOfMonth.setText(dateItems.get(6).getDayOfMonth());
            binding.dateSunday.textViewDayOfWeek.setText(dateItems.get(6).getDayOfWeek());
        }
    }

    private static class ViewHolderItemMainBlockMenu extends RecyclerView.ViewHolder {

        private final ItemMainBlockMenuBinding binding;

        public ViewHolderItemMainBlockMenu(ItemMainBlockMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ItemMainBlockMenu screenItem) {
            binding.textViewButtonExerciseCompleted.setText(screenItem.getButtonText());
        }
    }
}