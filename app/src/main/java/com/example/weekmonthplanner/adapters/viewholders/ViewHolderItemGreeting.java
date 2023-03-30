package com.example.weekmonthplanner.adapters.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weekmonthplanner.databinding.ItemGreetingBinding;
import com.example.weekmonthplanner.screen_items.ItemGreeting;

public class ViewHolderItemGreeting extends RecyclerView.ViewHolder {

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