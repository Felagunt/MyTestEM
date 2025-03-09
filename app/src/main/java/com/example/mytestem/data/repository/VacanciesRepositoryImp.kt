package com.example.mytestem.data.repository

import com.example.mytestem.data.mappers.toOffer
import com.example.mytestem.data.mappers.toVacancy
import com.example.mytestem.data.network.VacancyApi
import com.example.mytestem.domain.models.Offer
import com.example.mytestem.domain.models.Vacancy
import com.example.mytestem.domain.repository.VacanciesRepository
import javax.inject.Inject

class VacanciesRepositoryImp @Inject constructor(
    private val api: VacancyApi
): VacanciesRepository {

    override suspend fun getOffers(): List<Offer> {
        return api.getOffers().map { it.toOffer() }
    }

    override suspend fun getVacancies(): List<Vacancy> {
        return api.getVacancies().map { it.toVacancy() }
    }
}