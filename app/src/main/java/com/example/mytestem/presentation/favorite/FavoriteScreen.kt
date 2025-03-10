package com.example.mytestem.presentation.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mytestem.domain.models.Vacancy
import com.example.mytestem.presentation.search.components.VacancyListItem

@Composable
fun FavoriteScreenRoot(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onClickVacancy: (Vacancy) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    FavoriteScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is FavoriteAction.OnResponseClick -> onClickVacancy(action.vacancy)
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Избранное",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "${state.vacancyList.size} вакансия"
        )
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
                Surface(
                    tonalElevation = 4.dp,
                    color = MaterialTheme.colorScheme.surface
                ) {
                    LazyColumn(
                        modifier = Modifier,
                    ) {
                        items(it) { vacancy ->
                            VacancyListItem(
                                vacancy = vacancy,
                                onFavoriteClick = {
                                    onAction(FavoriteAction.OnFavoriteClick(vacancy))
                                },
                                onResponseClick = {

                                },
                                modifier = Modifier
                                    .clickable {
                                        onAction(FavoriteAction.OnResponseClick(vacancy))
                                    }
                            )
                        }
                    }

                }
            }
        }

    }
}
