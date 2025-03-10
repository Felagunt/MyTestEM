package com.example.mytestem.presentation.favorite

import com.example.mytestem.domain.models.Vacancy

sealed interface FavoriteAction {
    data class OnResponseClick(val vacancy: Vacancy): FavoriteAction
    data class OnFavoriteClick(val vacancy: Vacancy): FavoriteAction
    data object OnBackClick: FavoriteAction
}