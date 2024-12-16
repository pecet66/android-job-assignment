package com.schibsted.nde.model

data class MealResponse(
    val idMeal: String,
    val strMeal: String,
    val strCategory: String,
    val strMealThumb: String,
    val strInstructions: String,
    val strYoutube: String?,
)