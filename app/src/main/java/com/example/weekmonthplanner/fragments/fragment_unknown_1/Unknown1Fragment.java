package com.example.weekmonthplanner.fragments.fragment_unknown_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.weekmonthplanner.R;
import com.example.weekmonthplanner.databinding.Unknown1FragmentBinding;

public class Unknown1Fragment extends Fragment {
    private Unknown1FragmentBinding binding;

    public Unknown1Fragment() {
        super(R.layout.unknown_1_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = Unknown1FragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}