package com.schibsted.nde.mapper

import com.schibsted.nde.database.MealEntity
import com.schibsted.nde.model.MealModel
import com.schibsted.nde.model.MealResponse


fun List<MealResponse>.toMealEntities(): List<MealEntity> {
    return this.map { mealResponse ->
        MealEntity(
            mealId = mealResponse.idMeal,
            strMeal = mealResponse.strMeal,
            strCategory = mealResponse.strCategory,
            strMealThumb = mealResponse.strMealThumb,
            strInstructions = mealResponse.strInstructions,
            strYoutube = mealResponse.strYoutube
        )
    }
}

fun List<MealEntity>.toMealModels(): List<MealModel> {
    return this.map { mealEntity ->
        MealModel(
            mealId = mealEntity.mealId,
            strMeal = mealEntity.strMeal,
            strCategory = mealEntity.strCategory,
            strMealThumb = mealEntity.strMealThumb,
            strInstructions = mealEntity.strInstructions,
            strYoutube = mealEntity.strYoutube
        )
    }
}

fun List<MealResponse>.toMealModelss(): List<MealModel> {
    return this.map { mealResponse ->
        MealModel(
            mealId = mealResponse.idMeal,
            strMeal = mealResponse.strMeal,
            strCategory = mealResponse.strCategory,
            strMealThumb = mealResponse.strMealThumb,
            strInstructions = mealResponse.strInstructions,
            strYoutube = mealResponse.strYoutube
        )
    }
}