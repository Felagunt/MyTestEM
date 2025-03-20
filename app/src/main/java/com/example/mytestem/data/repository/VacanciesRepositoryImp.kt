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

    override suspend fun getOffers(): Result<List<Offer>> {
        return try {
            val response = api.getOffers()
            if(response.isSuccessful) {
                response.body()?.let { list ->
                    Result.success(
                        list
                            .map {
                                it.toOffer()
                            }
                    )
                } ?: run { Result.failure(Exception("Error")) }
            } else {
                Result.failure(Exception("Smth wrong:"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFavoriteVacancies(): Result<List<Vacancy>> {
        return try {
            val response = api.getVacancies()
            if(response.isSuccessful) {
                response.body()?.let { list ->
                    Result.success(
                        list
                            .filter { it.isFavorite }
                            .map {
                                it.toVacancy()
                            }
                    )
                } ?: run { Result.failure(Exception("Error")) }
            } else {
                Result.failure(Exception("Smth wrong:"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getVacancies(): Result<List<Vacancy>> {
        return try {
            val response = api.getVacancies()
            if(response.isSuccessful) {
                response.body()?.let { list ->
                    Result.success(
                        list
                            .map {
                                it.toVacancy()
                            }
                    )
                } ?: run { Result.failure(Exception("Error")) }
            } else {
                Result.failure(Exception("Smth wrong:"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getVacancyDetails(id: String): Result<Vacancy?> {
        return try {
            val response = api.getVacancies()
            if(response.isSuccessful) {
                response.body()?.let { list ->
                    Result.success(
                        list
                            .map {
                                it.toVacancy()
                            }
                            .firstOrNull{it.id == id}
                    )
                } ?: run { Result.failure(Exception("Error")) }
            } else {
                Result.failure(Exception("Smth wrong:"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}