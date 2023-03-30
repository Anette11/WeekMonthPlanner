package com.example.data.di;

import com.example.data.repositories.GetAllExercisesRepositoryImpl;
import com.example.data.repositories.SaveAllExercisesRepositoryImpl;
import com.example.domain.repositories.GetAllExercisesRepository;
import com.example.domain.repositories.SaveAllExercisesRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract GetAllExercisesRepository provideGetAllExercisesRepository(
            GetAllExercisesRepositoryImpl getAllExercisesRepositoryImpl
    );

    @Binds
    @Singleton
    abstract SaveAllExercisesRepository provideSaveAllExercisesRepository(
            SaveAllExercisesRepositoryImpl saveAllExercisesRepositoryImpl
    );
}