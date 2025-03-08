package com.example.mytestem.presentation.search

import com.example.mytestem.domain.models.Vacancy

sealed interface MainAction {
    data class OnVacancyClick(val vacancy: Vacancy): MainAction
    data class OnOfferClick(val link: String): MainAction
    data class OnSearchQueryChange(val query: String): MainAction
    data class OnFavoriteClick(val id: String): MainAction
}