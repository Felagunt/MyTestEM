package com.example.mytestem.presentation.vacancyDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.toRoute
import com.example.mytestem.Route.Route
import com.example.mytestem.core.domain.Resource
import com.example.mytestem.domain.use_cases.GetVacanciesUseCase
import com.example.mytestem.domain.use_cases.GetVacancyDetailsUseCase
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
class VacancyDetailsViewModel @Inject constructor(
    private val getVacancyDetailsUseCase: GetVacancyDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val vacancyId = savedStateHandle.toRoute<Route.VacancyDetails>().id

    private val _state = MutableStateFlow(VacancyDetailsState())
    val state = _state
        .onStart {
            getVacancies(vacancyId)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )
    
    fun onAction(action: VacancyDetailsAction) {
        when(action) {
            is VacancyDetailsAction.OnBackClick -> {
                
            }
            is VacancyDetailsAction.OnResponseClick -> {

            }

            is VacancyDetailsAction.OnFavoriteClick -> {

            }
        }
    }

    private fun getVacancies(id: String) {
        getVacancyDetailsUseCase
            .invoke(id)
            .onEach { result ->
                when(result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                vacancy = null,
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
                                vacancy = null
                            )
                        }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorMsg = null,
                                vacancy = result.data!!
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }
}