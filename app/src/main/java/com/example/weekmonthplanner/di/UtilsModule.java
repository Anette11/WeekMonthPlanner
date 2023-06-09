package com.example.weekmonthplanner.di;

import android.content.Context;

import com.example.weekmonthplanner.utils.ResourcesProvider;
import com.example.weekmonthplanner.utils.ResourcesProviderImpl;
import com.example.weekmonthplanner.utils.RxFragmentNotifier;
import com.example.weekmonthplanner.utils.WeekCreator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UtilsModule {

    @Provides
    @Singleton
    ResourcesProvider provideResourcesProvider(
            @ApplicationContext Context context
    ) {
        return new ResourcesProviderImpl(context);
    }

    @Provides
    @Singleton
    WeekCreator provideWeekCreator(
            ResourcesProvider resourcesProvider
    ) {
        return new WeekCreator(resourcesProvider);
    }

    @Provides
    @Singleton
    RxFragmentNotifier provideRxFragmentNotifier() {
        return new RxFragmentNotifier();
    }
}