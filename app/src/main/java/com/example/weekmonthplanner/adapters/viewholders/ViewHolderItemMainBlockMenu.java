package com.example.weekmonthplanner.adapters.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weekmonthplanner.databinding.ItemMainBlockMenuBinding;
import com.example.weekmonthplanner.screen_items.ItemMainBlockMenu;
import com.example.weekmonthplanner.utils.OnExerciseCompleteClick;

public class ViewHolderItemMainBlockMenu extends RecyclerView.ViewHolder {

    private final ItemMainBlockMenuBinding binding;
    private final OnExerciseCompleteClick onExerciseCompleteClick;

    public ViewHolderItemMainBlockMenu(
            ItemMainBlockMenuBinding binding,
            OnExerciseCompleteClick onExerciseCompleteClick
    ) {
        super(binding.getRoot());
        this.binding = binding;
        this.onExerciseCompleteClick = onExerciseCompleteClick;
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