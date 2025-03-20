package com.example.mytestem.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestem.core.domain.Resource
import com.example.mytestem.domain.use_cases.GetFavoriteVacanciesUseCase
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
class FavoriteViewModel @Inject constructor(
    private val getFavoriteVacanciesUseCase: GetFavoriteVacanciesUseCase
) : ViewModel() {



    private val _state = MutableStateFlow(FavoriteState())
    val state = _state
        .onStart {
            getVacancies()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: FavoriteAction) {
        when(action) {
            is FavoriteAction.OnResponseClick -> {

            }
            is FavoriteAction.OnBackClick -> {

            }
            is FavoriteAction.OnFavoriteClick -> {

            }
        }
    }

    private fun getVacancies() {
        getFavoriteVacanciesUseCase
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
}