package com.schibsted.nde.usecase

import com.schibsted.nde.data.MealsRepository
import com.schibsted.nde.model.MealResponse

class GetMealDetailsUseCase(
    private val mealsRepository: MealsRepository
) {

    suspend operator fun invoke(mealId: String): MealResponse? = mealsRepository.getMeals().find { it.idMeal == mealId }
}
