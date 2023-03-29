package com.example.weekmonthplanner.fragments.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weekmonthplanner.R;
import com.example.weekmonthplanner.adapters.CalendarRecyclerViewAdapter;
import com.example.weekmonthplanner.databinding.CalendarFragmentBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CalendarFragment extends Fragment {

    @Inject
    CalendarRecyclerViewAdapter calendarRecyclerViewAdapter;
    private CalendarFragmentBinding binding;

    public CalendarFragment() {
        super(R.layout.calendar_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = CalendarFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        CalendarViewModel calendarViewModel = new ViewModelProvider(requireActivity()).get(CalendarViewModel.class);

        binding.recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(calendarRecyclerViewAdapter);

        calendarViewModel.screenItems.observe(getViewLifecycleOwner(), screenItems -> {
            if (screenItems != null) {
                calendarRecyclerViewAdapter.updateList(screenItems);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
