package com.schibsted.nde.di

import com.schibsted.nde.data.MealsRepository
import com.schibsted.nde.usecase.GetMealDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {

    @Singleton
    @Provides
    fun provideGetMealDetailsUseCase(mealsRepository: MealsRepository): GetMealDetailsUseCase =
        GetMealDetailsUseCase(mealsRepository)

}