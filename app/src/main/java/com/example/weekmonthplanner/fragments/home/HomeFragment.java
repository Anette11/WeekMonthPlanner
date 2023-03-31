package com.example.weekmonthplanner.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weekmonthplanner.MainActivity;
import com.example.weekmonthplanner.R;
import com.example.weekmonthplanner.adapters.HomeRecyclerViewAdapter;
import com.example.weekmonthplanner.databinding.HomeFragmentBinding;
import com.example.weekmonthplanner.utils.OnNotifyHomeFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment implements OnNotifyHomeFragment {

    @Inject
    HomeRecyclerViewAdapter homeRecyclerViewAdapter;

    private HomeFragmentBinding binding;
    private HomeViewModel homeViewModel;

    public HomeFragment() {
        super(R.layout.home_fragment);
    }

    @Override
    public void onCreate(
            @Nullable Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        ((MainActivity) requireActivity()).setOnNotifyHomeFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        homeRecyclerViewAdapter.setOnExerciseCompleteClick(homeViewModel::setOnExerciseCompleteClick);

        binding.recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(homeRecyclerViewAdapter);

        homeViewModel.screenItems.observe(getViewLifecycleOwner(), screenItems -> {
            if (screenItems != null) {
                homeRecyclerViewAdapter.updateList(screenItems);
            }
        });
    }

    @Override
    public void onNotify() {
        homeViewModel.onNotify();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}