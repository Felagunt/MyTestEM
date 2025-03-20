package com.example.mytestem.domain.repository

import com.example.mytestem.domain.models.Offer
import com.example.mytestem.domain.models.Vacancy

interface VacanciesRepository {

    suspend fun getVacancies(): Result<List<Vacancy>>

    suspend fun getFavoriteVacancies(): Result<List<Vacancy>>

    suspend fun getOffers(): Result<List<Offer>>

    suspend fun getVacancyDetails(id: String): Result<Vacancy?>
}