package com.example.mytestem.domain.repository

import com.example.mytestem.domain.models.Offer
import com.example.mytestem.domain.models.Vacancy

interface VacanciesRepository {

    suspend fun getVacancies(): List<Vacancy>

    suspend fun getOffers(): List<Offer>
}