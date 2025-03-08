package com.example.mytestem.presentation.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mytestem.domain.models.Vacancy
import com.example.mytestem.presentation.search.MainAction
import com.example.mytestem.presentation.search.MainViewModel
import com.example.mytestem.presentation.search.components.VacancyListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreenRoot(
    viewModel: FavoriteViewModel = koinViewModel(),
    onClickVacancy: (Vacancy) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    FavoriteScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is FavoriteAction.OnVacancyClick -> onClickVacancy(action.vacancy)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun FavoriteScreen(
    state: FavoriteState,
    onAction: (FavoriteAction) -> Unit
) {
    Surface(
        tonalElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
            )
        }
        if (state.errorMsg?.isNotBlank() == true) {
            Text(
                text = state.errorMsg,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.background(MaterialTheme.colorScheme.surface)
            )
        }
        state.vacancyList.let {
            LazyColumn(
                modifier = Modifier,
            ) {
                items(it) { vacancy ->
                    VacancyListItem(
                        vacancy = vacancy,
                        onFavoriteClick = {
                            onAction(FavoriteAction.OnFavoriteClick(vacancy.id))
                        },
                        onResponseClick = {

                        },
                        modifier = Modifier
                            .clickable {
                                onAction(FavoriteAction.OnVacancyClick(vacancy))
                            }
                    )
                }
            }
        }
    }
}
