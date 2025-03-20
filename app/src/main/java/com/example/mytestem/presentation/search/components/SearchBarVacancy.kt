package com.example.mytestem.presentation.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SearchBarVacancy(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onImeSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Surface(
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 4.dp
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                shape = RoundedCornerShape(100),
                colors = OutlinedTextFieldDefaults.colors(
                    //cursorColor = DarkBlue,
                    //focusedBorderColor = SandYellow
                ),
                placeholder = {
                    Text(
                        text = "Должность, ключеввые слова"
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.66f)
                    )
                },
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onImeSearch()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                trailingIcon = {
                    AnimatedVisibility(
                        visible = searchQuery.isNotBlank()
                    ) {
                        IconButton(
                            onClick = {
                                onSearchQueryChange("")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                },
                modifier = modifier
                    .background(
                        shape = RoundedCornerShape(100),
                        color = MaterialTheme.colorScheme.background
                    )
                    .minimumInteractiveComponentSize()
            )
        }

        Surface(
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 4.dp
        ) {
            Icon(
                Icons.Default.Menu,
                contentDescription = null,
                modifier = Modifier
                    .weight(0.2f)
            )
        }
    }
}