package com.example.mytestem.Route

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.mytestem.presentation.favorite.FavoriteScreenRoot
import com.example.mytestem.presentation.favorite.FavoriteViewModel
import com.example.mytestem.presentation.messages.MessagesScreen
import com.example.mytestem.presentation.profile.ProfileScreen
import com.example.mytestem.presentation.responses.ResponseScreen
import com.example.mytestem.presentation.search.MainScreenRoot
import com.example.mytestem.presentation.search.MainViewModel
import com.example.mytestem.presentation.vacancyDetails.VacancyDetailsScreenRoot
import com.example.mytestem.presentation.vacancyDetails.VacancyDetailsViewModel

@Composable
fun BottomBar() {
    MaterialTheme {


        val navController = rememberNavController()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
            ?: BottomNavigation.SEARCH.route::class.qualifiedName.orEmpty()

        val currentRouteTrimmed by remember(currentRoute) {
            derivedStateOf { currentRoute.substringBefore("?") }
        }

        Scaffold(
            bottomBar = {
                BottomAppBar {
                    BottomNavigation.entries
                        .forEachIndexed { index, navigationItem ->


                            val isSelected by remember(currentRoute) {
                                derivedStateOf { currentRouteTrimmed == navigationItem.route::class.qualifiedName }
                            }


                            NavigationBarItem(
                                selected = isSelected,
                                label = { Text(navigationItem.label) },
                                icon = {
                                    Icon(
                                        imageVector = navigationItem.icon,
                                        contentDescription = navigationItem.label
                                    )
                                },
                                onClick = {
                                    navController.navigate(navigationItem.route)
                                }
                            )
                        }
                }
            }
        ) {

            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                navController = navController,
                startDestination = Route.RootGraph
            ) {
                navigation<Route.RootGraph>(
                    startDestination = Route.VacancyList,
                ) {

                    composable<Route.VacancyList> {
                        val viewModel = hiltViewModel<MainViewModel>()
                        MainScreenRoot(
                            viewModel = viewModel,
                            onClickVacancy = { vacancy ->
                                navController.navigate(
                                    Route.VacancyDetails(vacancy.id)
                                )
                            }
                        )
                    }

                    composable<Route.VacancyDetails> {
                        val viewmodel = hiltViewModel<VacancyDetailsViewModel>()
                        VacancyDetailsScreenRoot(
                            viewModel = viewmodel,
                            onBackClick = {
                                navController.navigateUp()
                            },
                            onResponseClick = {vacancy ->
                                Route.Favorite
                            }
                        )
                    }
                    composable<Route.Favorite> {
                        val viewModel = hiltViewModel<FavoriteViewModel>()
                        FavoriteScreenRoot(
                            viewModel = viewModel,
                            onClickVacancy = { vacancy ->
                                navController.navigate(
                                    Route.VacancyDetails(vacancy.id)
                                )
                            }
                        )
                    }
                    composable<Route.Response> {
                        ResponseScreen()
                    }

                    composable<Route.Messages> {
                        MessagesScreen()
                    }
                    composable<Route.Profile> {
                        ProfileScreen()
                    }
                }
            }
        }
    }
}