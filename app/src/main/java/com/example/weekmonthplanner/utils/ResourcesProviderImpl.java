package com.example.weekmonthplanner.utils;

import android.content.Context;

import androidx.annotation.StringRes;

public class ResourcesProviderImpl implements ResourcesProvider {
    private final Context context;

    public ResourcesProviderImpl(Context context) {
        this.context = context;
    }

    @Override
    public String getString(@StringRes int stringRes) {
        return context.getString(stringRes);
    }
}
