package com.example.mytestem.Route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.mytestem.presentation.search.MainScreenRoot

@Composable
fun RootGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.RootGraph
    ) {
        navigation<Route.RootGraph>(
            startDestination = Route.RootGraph
        ) {
            composable<Route.VacancyList> {
                MainScreenRoot(

                )
            }
            composable<Route.VacancyDetails> {

            }
        }
    }
}