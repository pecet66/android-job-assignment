package com.schibsted.nde.model

data class MealModel(
    val mealId: String,
    val strMeal: String,
    val strCategory: String,
    val strMealThumb: String,
    val strInstructions: String,
    val strYoutube: String?,
)