package com.example.mytestem.presentation.favorite

import com.example.mytestem.domain.models.Vacancy

sealed interface FavoriteAction {
    data class OnVacancyClick(val vacancy: Vacancy): FavoriteAction
    data class OnFavoriteClick(val id: String): FavoriteAction
}