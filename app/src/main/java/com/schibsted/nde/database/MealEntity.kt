package com.schibsted.nde.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class MealEntity(
    @PrimaryKey val mealId: String,
    val strMeal: String,
    val strCategory: String,
    val strMealThumb: String,
    val strInstructions: String,
    val strYoutube: String?
)
