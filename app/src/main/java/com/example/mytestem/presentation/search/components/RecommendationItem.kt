package com.example.mytestem.presentation.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mytestem.domain.models.Offer
import com.example.mytestem.domain.models.Vacancy

@Composable
fun RecommendationItem(
    offer: Offer,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        modifier = Modifier,
        color = MaterialTheme.colorScheme.surfaceTint
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

//            Image(
//                painter = painterResource(
//                    id = res/drawable/temporary.jpg//TODO
//
//                ),
//                contentDescription = null
//            )
            Text(
                text = offer.title,
                overflow = TextOverflow.Visible,
                maxLines = if(offer.button.text.isNotBlank()) 2  else 3
            )
            if(offer.button.text.isNotBlank()) {
                Text(
                    text = offer.button.text,
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }
    }
}