package com.example.weekmonthplanner.di;

import com.example.weekmonthplanner.data.ExerciseDao;
import com.example.weekmonthplanner.repositories.MainRepository;
import com.example.weekmonthplanner.repositories.MainRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoriesModule {

    @Provides
    @Singleton
    MainRepository provideMainRepository(ExerciseDao exerciseDao) {
        return new MainRepositoryImpl(exerciseDao);
    }
}