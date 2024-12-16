package com.schibsted.nde.feature.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schibsted.nde.usecase.GetMealDetailsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = MealDetailsViewModel.MealDetailsViewModelFactory::class)
class MealDetailsViewModel @AssistedInject constructor(
    @Assisted val mealId: String,
    private val getMealDetailsUseCase: GetMealDetailsUseCase
) : ViewModel() {

    @AssistedFactory
    interface MealDetailsViewModelFactory {
        fun create(id: String): MealDetailsViewModel
    }

    private val _state = mutableStateOf(MealDetailsState())
    val state: State<MealDetailsState> = _state

    private val _event = MutableSharedFlow<MealDetailsEvent>()
    val event = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                loadingContent = true
            )

            val mealDetails = getMealDetailsUseCase(mealId)
            _state.value = _state.value.copy(
                loadingContent = false,
                mealDetails = mealDetails
            )
        }
    }

    fun onEvent(event: MealDetailsEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

}
