package com.example.mytestem.presentation.search

import com.example.mytestem.domain.models.Vacancy

data class MainState(
    val vacancyList: List<Vacancy> = emptyList(),
    val searchVacancy: List<Vacancy> = emptyList(),
    val searchQuery: String? = null,
    val errorMsg: String? = null,
    val isLoading: Boolean = false
)