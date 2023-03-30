package com.example.weekmonthplanner.adapters.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weekmonthplanner.databinding.ItemTextLargeBinding;
import com.example.weekmonthplanner.screen_items.ItemTextLarge;

public class ViewHolderItemTextLarge extends RecyclerView.ViewHolder {

    private final ItemTextLargeBinding binding;

    public ViewHolderItemTextLarge(ItemTextLargeBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ItemTextLarge screenItem) {
        binding.textView.setText(screenItem.getText());
    }
}