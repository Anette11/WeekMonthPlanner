package com.example.weekmonthplanner.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weekmonthplanner.R;
import com.example.weekmonthplanner.adapters.HomeRecyclerViewAdapter;
import com.example.weekmonthplanner.databinding.HomeFragmentBinding;
import com.example.weekmonthplanner.screen_items.ItemGreeting;
import com.example.weekmonthplanner.screen_items.ItemMainBlockMenu;
import com.example.weekmonthplanner.screen_items.ItemWeek;
import com.example.weekmonthplanner.screen_items.ScreenItem;
import com.example.weekmonthplanner.utils.DateItem;
import com.example.weekmonthplanner.utils.WeekCreator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private HomeFragmentBinding binding;

    public HomeFragment() {
        super(R.layout.home_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setRecyclerView() {
        List<ScreenItem> list = new ArrayList<>();
        list.add(new ItemGreeting(getString(R.string.greeting), getString(R.string.full_name), R.drawable.abb70d9a47429527d540bd9a3d7aae8f));
        List<DateItem> dateItems = new WeekCreator().createDateItems();
        list.add(new ItemWeek(dateItems));
        list.add(new ItemMainBlockMenu("Exercise1 completed"));
        HomeRecyclerViewAdapter homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(list);
        binding.recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(homeRecyclerViewAdapter);
    }
}