package com.example.mytestem.presentation.vacancyDetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mytestem.domain.models.Vacancy

@Composable
fun VacancyHeader(
    vacancy: Vacancy,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = vacancy.title,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = vacancy.salary.full,
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = vacancy.experience.previewText,
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = vacancy.schedules.toString(),
            style = MaterialTheme.typography.bodySmall
        )

    }
}