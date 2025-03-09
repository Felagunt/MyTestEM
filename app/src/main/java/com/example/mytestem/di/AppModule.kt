package com.example.mytestem.di

import com.example.mytestem.data.network.VacancyApi
import com.example.mytestem.data.repository.VacanciesRepositoryImp
import com.example.mytestem.domain.repository.VacanciesRepository
import com.example.mytestem.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideVacanciesApi(): VacancyApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VacancyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideVacanciesRepository(api: VacancyApi): VacanciesRepository {
        return VacanciesRepositoryImp(
            api = api
        )
    }
}