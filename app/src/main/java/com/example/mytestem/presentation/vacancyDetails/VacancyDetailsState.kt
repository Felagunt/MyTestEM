package com.example.mytestem.presentation.vacancyDetails

import com.example.mytestem.domain.models.Vacancy

data class VacancyDetailsState(
    val vacancy: Vacancy? = null,
    val errorMsg: String? = null,
    val isLoading: Boolean = false
)