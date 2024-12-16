package com.schibsted.nde.feature.details

import com.schibsted.nde.model.MealResponse

data class MealDetailsState(
    val loadingContent: Boolean = false,
    val mealDetails: MealResponse? = null
)

sealed interface MealDetailsEvent {
    data object NavigateBack : MealDetailsEvent
}
