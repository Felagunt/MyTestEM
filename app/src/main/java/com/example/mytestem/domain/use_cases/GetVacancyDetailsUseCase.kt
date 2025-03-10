package com.example.mytestem.domain.use_cases

import com.example.mytestem.core.domain.Resource
import com.example.mytestem.domain.models.Vacancy
import com.example.mytestem.domain.repository.VacanciesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetVacancyDetailsUseCase @Inject constructor(
    private val repository: VacanciesRepository
) {

    operator fun invoke(id: String): Flow<Resource<Vacancy?>> = flow {
        try {
            emit(Resource.Loading())
            val list = repository.getVacancyDetails(id)
            emit(Resource.Success(list))
        } catch (e: HttpException) {
            emit(Resource.Error("Something wrong: " + e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. " + e.localizedMessage))
        }
    }
}