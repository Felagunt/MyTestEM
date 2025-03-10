package com.example.mytestem.presentation.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuestionsChip(
    question: String,
    modifier: Modifier = Modifier
) {

    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 4.dp
    ) {
            Text(
                text = question,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(4.dp)
            )

        }

    }