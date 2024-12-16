package com.schibsted.nde.feature.details

import com.schibsted.nde.model.MealModel

data class MealDetailsState(
    val loadingContent: Boolean = false,
    val mealDetails: MealModel? = null
)

sealed interface MealDetailsEvent {
    data object NavigateBack : MealDetailsEvent
}
