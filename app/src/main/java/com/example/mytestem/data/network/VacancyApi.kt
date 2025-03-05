package com.example.mytestem.data.network

import com.example.mytestem.data.dto.ResponseDto
import retrofit2.http.GET

interface VacancyApi {

    @GET
    suspend fun getVacancy(): ResponseDto
}