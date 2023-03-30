package com.example.weekmonthplanner.fragments.fragment_unknown_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.weekmonthplanner.R;
import com.example.weekmonthplanner.databinding.Unknown2FragmentBinding;

public class Unknown2Fragment extends Fragment {
    private Unknown2FragmentBinding binding;

    public Unknown2Fragment() {
        super(R.layout.unknown_2_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = Unknown2FragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}