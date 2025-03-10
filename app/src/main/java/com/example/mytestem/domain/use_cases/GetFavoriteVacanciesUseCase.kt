package com.example.mytestem.domain.use_cases

import com.example.mytestem.core.domain.Resource
import com.example.mytestem.domain.models.Vacancy
import com.example.mytestem.domain.repository.VacanciesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetFavoriteVacanciesUseCase @Inject constructor(
    private val repository: VacanciesRepository
) {

    operator fun invoke(): Flow<Resource<List<Vacancy>>> = flow {
        try {
            emit(Resource.Loading())
            val list = repository.getVacancies().filter { it.isFavorite }
            emit(Resource.Success(list))
        } catch (e: HttpException) {
            emit(Resource.Error("Something wrong: " + e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. " + e.localizedMessage))
        }
    }
}