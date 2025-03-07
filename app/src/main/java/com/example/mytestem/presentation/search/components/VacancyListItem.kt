package com.example.mytestem.presentation.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytestem.domain.models.Vacancy
import com.example.mytestem.util.toDate

@Composable
fun VacancyListItem(
    vacancy: Vacancy,
    modifier: Modifier = Modifier,
    date: String,
    onFavoriteClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        modifier = Modifier,
        color = MaterialTheme.colorScheme.surfaceTint
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Сейчас просматривает ${vacancy.lookingNumber} человек",//TODO
                    style = MaterialTheme.typography.titleLarge
                )
                IconButton(
                    onClick = {
                        onFavoriteClick()
                    }
                ) {
                    Icon(
                        imageVector = if (vacancy.isFavorite) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Filled.FavoriteBorder
                        },
                        tint = if(vacancy.isFavorite) {
                            MaterialTheme.colorScheme.secondaryContainer
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        },
                        contentDescription = if (vacancy.isFavorite) {
                            "Add to favorite"
                        } else {
                            "Remove from favorite"
                        }
                    )
                }
            }
            Text(
                text = vacancy.title,
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                //content = Arrangement.spacedBy(5.dp)//TODO
            ) {
                Text(
                    text = vacancy.address.town,
                    style = MaterialTheme.typography.bodySmall
                )
                Icon(Icons.Default.CheckCircle, contentDescription = null)
            }
            Row {
                Icon(Icons.Default.Info, contentDescription = null)
                Text(text = vacancy.experience.previewText)
            }
            Text(text = "Опубликовано ${vacancy.publishedDate.toDate().dayOfMonth} " + vacancy.publishedDate.toDate().month )
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                shape = RoundedCornerShape(32.dp),
                onClick = {

                }
            ) {
                Text(text = "Откликнуться")
            }
        }
    }
}