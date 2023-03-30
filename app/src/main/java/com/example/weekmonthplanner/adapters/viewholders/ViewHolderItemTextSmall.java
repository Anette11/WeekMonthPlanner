package com.example.weekmonthplanner.adapters.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weekmonthplanner.databinding.ItemTextSmallBinding;
import com.example.weekmonthplanner.screen_items.ItemTextSmall;

public class ViewHolderItemTextSmall extends RecyclerView.ViewHolder {

    private final ItemTextSmallBinding binding;

    public ViewHolderItemTextSmall(ItemTextSmallBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ItemTextSmall screenItem) {
        binding.textView.setText(screenItem.getText());
    }
}