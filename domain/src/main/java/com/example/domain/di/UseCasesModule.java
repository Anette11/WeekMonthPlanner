package com.example.domain.di;

import com.example.domain.repositories.GetAllExercisesRepository;
import com.example.domain.repositories.SaveAllExercisesRepository;
import com.example.domain.use_cases.GetAllExercisesUseCase;
import com.example.domain.use_cases.SaveAllExercisesUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCasesModule {

    @Provides
    @Singleton
    GetAllExercisesUseCase provideGetAllExercisesUseCase(
            GetAllExercisesRepository getAllExercisesRepository
    ) {
        return new GetAllExercisesUseCase(getAllExercisesRepository);
    }

    @Provides
    @Singleton
    SaveAllExercisesUseCase provideSaveAllExercisesUseCase(
            SaveAllExercisesRepository saveAllExercisesRepository
    ) {
        return new SaveAllExercisesUseCase(saveAllExercisesRepository);
    }
}