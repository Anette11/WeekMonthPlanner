package com.example.weekmonthplanner.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import timber.log.Timber;

public class TimeChangedBroadcastReceiver extends BroadcastReceiver {

    private final TimeChangedListener timeChangedListener;

    public TimeChangedBroadcastReceiver(
            TimeChangedListener timeChangedListener
    ) {
        this.timeChangedListener = timeChangedListener;
    }

    @Override
    public void onReceive(
            Context context,
            Intent intent
    ) {
        final String action = intent.getAction();
        if (action.equals(Intent.ACTION_TIME_CHANGED) ||
                action.equals(Intent.ACTION_DATE_CHANGED) ||
                action.equals(Intent.ACTION_TIME_TICK) ||
                action.equals(Intent.ACTION_TIMEZONE_CHANGED)
        ) {
            Timber.e(action);
            timeChangedListener.onTimeChanged();
        }
    }
}