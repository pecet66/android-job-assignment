package com.schibsted.nde.data

import com.schibsted.nde.api.MealsNetworkDataSource
import com.schibsted.nde.database.MealsRoomDataSource
import com.schibsted.nde.mapper.toMealEntities
import com.schibsted.nde.mapper.toMealModels
import com.schibsted.nde.mapper.toMealModelss
import com.schibsted.nde.model.MealModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealsRepository @Inject constructor(
    private val mealsNetworkDataSource: MealsNetworkDataSource,
    private val mealsRoomDataSource: MealsRoomDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getMeals(refresh: Boolean): List<MealModel> =
        withContext(dispatcher) {
            // Get the meals from the DB.
            val meals = mealsRoomDataSource.getMealsList().toMealModels()

            return@withContext if (refresh || meals.isEmpty()) {
                // Get the meals from the BE.
                val mealsResponse = mealsNetworkDataSource.getMealsList().meals
                mealsRoomDataSource.storeMeals(mealsResponse.toMealEntities())
                mealsResponse.toMealModelss()
            } else {
                meals
            }
        }

}