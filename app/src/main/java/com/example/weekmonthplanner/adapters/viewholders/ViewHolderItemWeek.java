package com.example.weekmonthplanner.adapters.viewholders;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weekmonthplanner.databinding.ItemDateBinding;
import com.example.weekmonthplanner.databinding.ItemWeekBinding;
import com.example.weekmonthplanner.screen_items.ItemWeek;
import com.example.weekmonthplanner.utils.DateItem;

import java.util.List;

public class ViewHolderItemWeek extends RecyclerView.ViewHolder {

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