package com.example.mytestem.presentation.favorite

import com.example.mytestem.domain.models.Offer
import com.example.mytestem.domain.models.Vacancy

data class FavoriteState(
    val vacancyList: List<Vacancy> = emptyList(),
    val errorMsg: String? = null,
    val isLoading: Boolean = false
)