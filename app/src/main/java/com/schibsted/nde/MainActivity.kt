package com.schibsted.nde

import android.R
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.schibsted.nde.feature.details.MealDetailScreen
import com.schibsted.nde.feature.details.MealDetailsEvent
import com.schibsted.nde.feature.details.MealDetailsViewModel
import com.schibsted.nde.feature.meals.MealsScreen
import com.schibsted.nde.feature.meals.MealsViewEvent
import com.schibsted.nde.feature.meals.MealsViewModel
import com.schibsted.nde.ui.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            AppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    SetupNavGraph(navController)
                }
            }
        }
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MEALS_LIST_ROUTE
    ) {
        mealsListScreenGraph(navController)
        mealsDetailsScreenGraph(navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.mealsListScreenGraph(navController: NavHostController) {
    composable(
        route = MEALS_LIST_ROUTE
    )
    {
        val viewModel: MealsViewModel = hiltViewModel()

        LaunchedEffect(Unit) {
            viewModel.event.collect { event ->
                when (event) {
                    is MealsViewEvent.NavigateToDetails -> navController.navigate("$MEALS_DETAILS_ROUTE/${event.mealId}")
                }
            }
        }

        MealsScreen(viewModel)
    }
}

private fun NavGraphBuilder.mealsDetailsScreenGraph(navController: NavHostController) {
    composable(
        route = "$MEALS_DETAILS_ROUTE/{$MEAL_ID_ARG}",
        arguments = listOf(navArgument(MEAL_ID_ARG) { type = NavType.StringType })
    ) { backStackEntry ->

        val mealId = backStackEntry.arguments?.getString(MEAL_ID_ARG) ?: ""
        val viewModel: MealDetailsViewModel =
            hiltViewModel<MealDetailsViewModel, MealDetailsViewModel.MealDetailsViewModelFactory> { factory ->
                factory.create(mealId)
            }
        val state by remember { viewModel.state }

        LaunchedEffect(Unit) {
            viewModel.event.collect { event ->
                when (event) {
                    is MealDetailsEvent.NavigateBack -> navController.navigateUp()
                }
            }
        }

        MealDetailScreen(
            state = state
        ) { event ->
            viewModel.onEvent(event)
        }
    }
}

const val MEAL_ID_ARG = "mealId"
const val MEALS_LIST_ROUTE = "meals"
const val MEALS_DETAILS_ROUTE = "meals_details"

data class MealDetails(val name: String)