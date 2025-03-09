package com.example.mytestem.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mytestem.core.domain.Resource
import com.example.mytestem.domain.repository.VacanciesRepository
import com.example.mytestem.domain.use_cases.GetOffersUseCase
import com.example.mytestem.domain.use_cases.GetVacanciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val getOffersUseCase: GetOffersUseCase
): ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state
        .onStart {
            getOffers()
            getVacancies()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

        fun onAction(action: MainAction) {
            when(action) {
                is MainAction.OnSearchQueryChange -> {

                }
                is MainAction.OnVacancyClick -> {

                }
                is MainAction.OnFavoriteClick -> {

                }
                is MainAction.OnOfferClick -> {

                }
            }
        }

    private fun getVacancies() {
        getVacanciesUseCase
            .invoke()
            .onEach { result ->
                when(result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                vacancyList = emptyList(),
                                errorMsg = result.message,
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                errorMsg = null,
                                vacancyList = emptyList()
                            )
                        }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorMsg = null,
                                vacancyList = result.data!!
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun getOffers() {
        getOffersUseCase
            .invoke()
            .onEach { result ->
                when(result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                offerList = emptyList(),
                                errorMsg = result.message,
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                errorMsg = null,
                                offerList = emptyList()
                            )
                        }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorMsg = null,
                                offerList = result.data!!
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }




}