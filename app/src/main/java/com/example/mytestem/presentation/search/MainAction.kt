package com.example.mytestem.presentation.search

import com.example.mytestem.domain.models.Vacancy

sealed interface MainAction {
    data class OnVacancyClick(val vacancy: Vacancy): MainAction
    data class OnSearchQueryChange(val query: String): MainAction
}