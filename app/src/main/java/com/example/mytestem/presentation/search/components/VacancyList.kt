package com.example.mytestem.presentation.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mytestem.Route.Route
import com.example.mytestem.domain.models.Vacancy
import com.example.mytestem.presentation.search.MainAction
import com.example.mytestem.presentation.search.MainState
import com.example.mytestem.util.rememberScrollContext

//@Composable
//fun VacancyList(
//    vacancy: List<Vacancy>,
//    onAction: () -> Unit
//) {
//    val listState = rememberLazyListState()
//    val initialList = vacancy.subList(1,4)
//    val leastList = vacancy.subList(5,vacancy.lastIndex)
//    Box {
//        LazyColumn(
//            modifier = Modifier,
//            state = listState
//        ) {
//            items(initialList) {vacancy ->
//                VacancyListItem(
//                    vacancy = vacancy,
//                    onFavoriteClick = {
//                        onAction(MainAction.OnFavoriteClick(vacancy.id))
//                    },
//                )
//            }
//
//            val scrollContext = rememberScrollContext(listState)
//
//            AnimatedVisibility(scrollContext.isBottom) {
//                Button(
//                    onClick = {
//                        items(leastList) {vacancy ->
//                            VacancyListItem(
//                                vacancy = vacancy,
//                                onFavoriteClick = {
//                                    onAction(MainAction.OnFavoriteClick(vacancy.id))
//                                }
//                            )
//                        }
//                    }
//                ) {
//                    Text("Ещё ${leastList.size} вакансии ")
//                }
//            }
//        }
//
//    }
//}