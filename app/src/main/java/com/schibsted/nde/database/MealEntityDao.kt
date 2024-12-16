package com.schibsted.nde.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MealEntityDao {

    @Query("SELECT * FROM meal ORDER BY mealId")
    fun getAll(): List<MealEntity>

    @Insert
    fun insertMeals(mealEntities: List<MealEntity>)

}