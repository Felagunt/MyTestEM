package com.example.mytestem.presentation.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mytestem.domain.models.Vacancy
import com.example.mytestem.presentation.search.components.RecommendationItem
import com.example.mytestem.presentation.search.components.SearchBarVacancy
import com.example.mytestem.presentation.search.components.VacancyListItem
import com.example.mytestem.util.rememberScrollContext
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreenRoot(
    viewModel: MainViewModel = koinViewModel(),
    onClickVacancy: (Vacancy) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    MainScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is MainAction.OnVacancyClick -> onClickVacancy(action.vacancy)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun MainScreen(
    state: MainState,
    onAction: (MainAction) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var showMore by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val lastVisible =
        listState.layoutInfo.visibleItemsInfo.lastIndex //+ listState.firstVisibleItemIndex
    val scrollContext = rememberScrollContext(listState)
    val itemList = remember {
        mutableListOf(
            state.vacancyList.subList(1,4)
        )
    }
    val initialList = state.vacancyList.subList(1, 4)
    val leastList = state.vacancyList.subList(5, state.offerList.lastIndex)

    Scaffold(
        topBar = {
            SearchBarVacancy(
                searchQuery = state.searchQuery ?: "",
                onSearchQueryChange = {
                    onAction(MainAction.OnSearchQueryChange(it))
                },
                onImeSearch = {
                    keyboardController?.hide()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (state.offerList.isNotEmpty()) {
                LazyRow(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(
                        items = state.offerList
                    ) { offer ->
                        RecommendationItem(
                            offer = offer
                        )
                    }
                }
            }
            Box {
                LazyColumn(
                    modifier = Modifier,
                    state = listState
                ) {
                    items(initialList) { vacancy ->
                        VacancyListItem(
                            vacancy = vacancy,
                            onFavoriteClick = {
                                onAction(MainAction.OnFavoriteClick(vacancy.id))
                            },
                            onResponseClick = {

                            },
                            modifier = Modifier
                                .clickable {
                                    onAction(MainAction.OnVacancyClick(vacancy))
                                }
                        )
                    }
                    if (showMore) {
                        itemList.add(4,leastList)
//                        items(leastList) { vacancy ->
//                            VacancyListItem(
//                                vacancy = vacancy,
//                                onFavoriteClick = {
//                                    onAction(MainAction.OnFavoriteClick(vacancy.id))
//                                }
//                            )
                        }
                    }
                }
                this@Column.AnimatedVisibility(scrollContext.isBottom) {
                    Button(
                        onClick = {
                            showMore = true
                            itemList.add(4,leastList)
                        }
                    ) {
                        Text("Ещё ${leastList.size} вакансии ")
                    }
                }

            }
        }
    }

