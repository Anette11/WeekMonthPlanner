package com.example.weekmonthplanner.adapters.viewholders;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weekmonthplanner.databinding.ItemExerciseBinding;
import com.example.weekmonthplanner.screen_items.ItemExercise;

public class ViewHolderItemExercise extends RecyclerView.ViewHolder {

    private final ItemExerciseBinding binding;

    public ViewHolderItemExercise(ItemExerciseBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ItemExercise screenItem) {
        binding.textViewDate.setText(screenItem.getDate());
        binding.textViewExerciseNumber.setText(screenItem.getExerciseNumber());
        binding.cardViewExercise.setStrokeColor(
                ContextCompat.getColor(binding.getRoot().getContext(),
                        screenItem.getColorInt()));
    }
}