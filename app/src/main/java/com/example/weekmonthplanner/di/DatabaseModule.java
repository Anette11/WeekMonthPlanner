package com.example.weekmonthplanner.di;

import android.content.Context;

import androidx.room.Room;

import com.example.weekmonthplanner.data.AppDatabase;
import com.example.weekmonthplanner.data.ExerciseDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(
            @ApplicationContext Context context
    ) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                AppDatabase.name
        ).build();
    }

    @Provides
    @Singleton
    ExerciseDao provideExerciseDao(
            AppDatabase appDatabase
    ) {
        return appDatabase.exerciseDao();
    }
}