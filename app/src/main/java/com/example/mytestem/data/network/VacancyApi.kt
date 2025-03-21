package com.example.mytestem.data.network

import com.example.mytestem.data.dto.OfferDto
import com.example.mytestem.data.dto.ResponseDto
import com.example.mytestem.data.dto.VacancyDto
import retrofit2.Response
import retrofit2.http.GET

interface VacancyApi {
    //http://localhost:3000/offers
    //http://localhost:3000/vacancies

    @GET
    suspend fun getAll(): ResponseDto

    @GET("/vacancies")
    suspend fun getVacancies(): Response<List<VacancyDto>>

    @GET("/offers")
    suspend fun getOffers(): Response<List<OfferDto>>
}