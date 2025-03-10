package com.example.mytestem.presentation.vacancyDetails

import com.example.mytestem.domain.models.Vacancy

sealed interface VacancyDetailsAction {
    data class OnResponseClick(val vacancy: Vacancy): VacancyDetailsAction
    data object OnBackClick: VacancyDetailsAction
}