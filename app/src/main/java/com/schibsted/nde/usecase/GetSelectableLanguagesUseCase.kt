package com.schibsted.nde.usecase

import com.schibsted.nde.data.MealsRepository
import com.schibsted.nde.model.MealModel

class GetMealDetailsUseCase(
    private val mealsRepository: MealsRepository
) {

    suspend operator fun invoke(mealId: String): MealModel? = mealsRepository.getMeals(false).find { it.mealId == mealId }
}
