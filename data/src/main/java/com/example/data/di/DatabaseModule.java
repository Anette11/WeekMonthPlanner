package com.example.data.di;

import android.content.Context;

import androidx.room.Room;

import com.example.data.local.AppDatabase;
import com.example.data.local.ExerciseDao;

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