package com.example.mytestem.presentation.vacancyDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mytestem.domain.models.Vacancy
import com.example.mytestem.presentation.search.components.AddressChip
import com.example.mytestem.presentation.search.components.QuestionsChip
import com.example.mytestem.presentation.vacancyDetails.components.VacancyChip
import com.example.mytestem.presentation.vacancyDetails.components.VacancyHeader

@Composable
fun VacancyDetailsScreenRoot(
    viewModel: VacancyDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onResponseClick: (Vacancy) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    VacancyDetailsScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is VacancyDetailsAction.OnResponseClick -> onResponseClick(action.vacancy)
                is VacancyDetailsAction.OnBackClick -> onBackClick
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VacancyDetailsScreen(
    state: VacancyDetailsState,
    onAction: (VacancyDetailsAction) -> Unit
) {

    state.vacancy?.let { vacancy ->
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.systemBars),
            navigationIcon = {
                IconButton(
                    onClick = {
                        onAction(VacancyDetailsAction.OnBackClick)
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Navigate back"
                    )
                }
            },
            title = {
                Text(text = "")
            },
            actions = {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icons.Outlined.Face
                }
                IconButton(
                    onClick =  {

                    }
                ) {
                    Icon(
                        Icons.Filled.Share,
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = {
                        onAction(VacancyDetailsAction.OnFavoriteClick)
                    }) {
                    Icon(
                        imageVector = //Icons.Default.FavoriteBorder,
                        if (vacancy.isFavorite) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Outlined.FavoriteBorder
                        },
                        tint = if(vacancy.isFavorite) {
                            MaterialTheme.colorScheme.surfaceTint
                        } else {
                            MaterialTheme.colorScheme.onSecondaryContainer
                        },
                        contentDescription = //"Add to favorite"
                        if (vacancy.isFavorite) {
                            "Add to favorite"
                        } else {
                            "Remove from favorite"
                        }
                    )
                }
            },
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth(Alignment.Start),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
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
            VacancyHeader(
                vacancy = vacancy
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                VacancyChip(
                    text = "${vacancy.appliedNumber} человек уже откликнулись",
                    modifier = Modifier
                )
                VacancyChip(
                    text = "${vacancy.appliedNumber} человек уже откликнулись",
                    modifier = Modifier
                )
            }
            AddressChip(
                vacancy = vacancy
            )
            Text(
                text = vacancy.description,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Ваши задачи",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            )
            Text(
                text = vacancy.responsibilities
            )
            Text(
                text = "Задайте впорос работодателю",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Он получит его с откликом на вакансию",
                style = MaterialTheme.typography.bodySmall
            )
            vacancy.questions.forEach { question ->
                QuestionsChip(question)
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    onAction(VacancyDetailsAction.OnResponseClick(vacancy))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Text(text = "Откликнутся")
            }
        }
    }
}