package com.schibsted.nde.api

import javax.inject.Inject

class MealsNetworkDataSource @Inject constructor(
    private val backendApi: BackendApi
) {
    suspend fun getMealsList() = backendApi.getMeals()
}