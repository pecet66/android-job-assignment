package com.schibsted.nde.feature.meals

import com.schibsted.nde.model.MealModel

data class MealsViewState(
    val meals: List<MealModel> = emptyList(),
    val filteredMeals: List<MealModel> = emptyList(),
    val isLoading: Boolean = false,
    val query: String? = null
)