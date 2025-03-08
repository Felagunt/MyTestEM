package com.example.mytestem.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class FavoriteViewModel(

) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state = _state
        .onStart {

        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: FavoriteAction) {
        when(action) {
            is FavoriteAction.OnFavoriteClick -> {

            }
            is FavoriteAction.OnVacancyClick -> {

            }
        }
    }

}