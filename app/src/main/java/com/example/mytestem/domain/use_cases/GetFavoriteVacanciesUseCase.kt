package com.example.mytestem.domain.use_cases

import com.example.mytestem.core.domain.Resource
import com.example.mytestem.domain.models.Vacancy
import com.example.mytestem.domain.repository.VacanciesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.io.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetFavoriteVacanciesUseCase @Inject constructor(
    private val repository: VacanciesRepository
) {

    operator fun invoke(): Flow<Resource<List<Vacancy>>> = flow {
        emit(Resource.Loading())
        val response = repository.getFavoriteVacancies()
        if (response.isSuccess) {
            emit(Resource.Success(data = response.getOrThrow()))
        } else {
            emit(Resource.Error(message = response.exceptionOrNull()?.localizedMessage.toString()))
        }
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}
//        try {
//            emit(Resource.Loading())
//            val list = repository.getVacancies().filter { it.isFavorite }
//            emit(Resource.Success(list))
//        } catch (e: HttpException) {
//            emit(Resource.Error("Something wrong: " + e.localizedMessage))
//        } catch (e: IOException) {
//            emit(Resource.Error("Couldn't reach server. " + e.localizedMessage))
//        }
//    }
//}