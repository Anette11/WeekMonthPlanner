package com.example.weekmonthplanner;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.weekmonthplanner.broadcast_receiver.TimeChangedBroadcastReceiver;
import com.example.weekmonthplanner.broadcast_receiver.TimeChangedListener;
import com.example.weekmonthplanner.databinding.ActivityMainBinding;
import com.example.weekmonthplanner.utils.OnNotifyCalendarFragment;
import com.example.weekmonthplanner.utils.OnNotifyHomeFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements TimeChangedListener {

    private ActivityMainBinding binding;
    private MainActivityViewModel mainActivityViewModel;
    private TimeChangedBroadcastReceiver timeChangedBroadcastReceiver;
    private OnNotifyCalendarFragment onNotifyCalendarFragment;
    private OnNotifyHomeFragment onNotifyHomeFragment;

    @Override
    protected void onCreate(
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        setNavigation();
        registerReceiver();
    }

    private void setNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container_view);
        if (navHostFragment != null) {
            NavigationUI.setupWithNavController(
                    binding.bottomNavigationView,
                    navHostFragment.getNavController());
        }
    }

    private void registerReceiver() {
        timeChangedBroadcastReceiver = new TimeChangedBroadcastReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
        intentFilter.addAction(Intent.ACTION_DATE_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        this.registerReceiver(timeChangedBroadcastReceiver, intentFilter);
    }

    @Override
    public void onTimeChanged() {
        mainActivityViewModel.onTimeChanged();
        onNotifyCalendarFragment.onNotify();
        onNotifyHomeFragment.onNotify();
    }

    public void setOnNotifyCalendarFragment(
            OnNotifyCalendarFragment onNotifyCalendarFragment
    ) {
        this.onNotifyCalendarFragment = onNotifyCalendarFragment;
    }

    public void setOnNotifyHomeFragment(
            OnNotifyHomeFragment onNotifyHomeFragment
    ) {
        this.onNotifyHomeFragment = onNotifyHomeFragment;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(timeChangedBroadcastReceiver);
    }
}