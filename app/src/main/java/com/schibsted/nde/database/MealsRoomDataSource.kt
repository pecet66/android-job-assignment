package com.schibsted.nde.database

import javax.inject.Inject

class MealsRoomDataSource @Inject constructor(
    private val mealEntityDao: MealEntityDao
) {
    fun getMealsList() = mealEntityDao.getAll()

    fun storeMeals(mealEntities: List<MealEntity>) = mealEntityDao.insertMeals(mealEntities)
}