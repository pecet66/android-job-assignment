package com.schibsted.nde.feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.schibsted.nde.R
import com.schibsted.nde.feature.common.MealImage
import com.schibsted.nde.model.MealModel
import com.schibsted.nde.ui.typography

@Composable
fun MealDetailScreen(
    state: MealDetailsState,
    modifier: Modifier = Modifier,
    onEvent: (event: MealDetailsEvent) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            androidx.compose.material.TopAppBar(
                title = { state.mealDetails?.strMeal?.let { Text(text = it) } },
                navigationIcon = {
                    androidx.compose.material.IconButton(onClick = { onEvent(MealDetailsEvent.NavigateBack) }) {
                        androidx.compose.material.Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        state.mealDetails?.let { MealDetailContent(padding, it) }
    }
}

@Composable
fun MealDetailContent(padding: PaddingValues, meal: MealModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MealImage(
            thumb = meal.strMealThumb,
            modifier = Modifier.size(240.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = meal.strMeal,
            style = typography.h5.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = stringResource(R.string.cooking_instructions),
            style = typography.h6.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = meal.strInstructions,
            style = typography.body1,
            modifier = Modifier.align(Alignment.Start)
        )
    }
}
