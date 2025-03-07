package com.example.mytestem.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mytestem.domain.models.Vacancy
import com.example.mytestem.presentation.search.components.SearchBarVacancy

@Composable
fun MainScreenRoot(
    viewModel: MainViewModel = hiltViewModel(),
    onClickVacancy: (Vacancy) -> Unit
) {

   val state by viewModel.state.collectAsStateWithLifecycle()

    MainScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is MainAction.OnVacancyClick -> onClickVacancy(action.vacancy)
                else -> Unit
            }
            viewModel.onEvent(action)
        }
    )

}

@Composable
private fun MainScreen(
    state: MainState,
    onAction: (MainAction) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            SearchBarVacancy(
                searchQuery = state.searchQuery,
                onSearchQueryChange = {

                },
                onImeSearch = {
                    keyboardController?.hide()
                }
            )
        }
    ) { paddingValues ->
        Column {

        }
    }


}