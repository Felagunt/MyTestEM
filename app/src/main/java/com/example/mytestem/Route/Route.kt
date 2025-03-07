package com.example.mytestem.Route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable


sealed interface Route {

    @Serializable
    data object VacancyGraph : Route

    @Serializable
    data object RootGraph : Route

    @Serializable
    data object VacancyList : Route

    @Serializable
    data class VacancyDetails(val id: String) : Route

    @Serializable
    data object Favorite : Route

    @Serializable
    data object Response : Route

    @Serializable
    data object Messages : Route

    @Serializable
    data object Profile : Route
}

enum class BottomNavigation(val label: String, val icon: ImageVector, val route: Route) {
    SEARCH("Search", Icons.Filled.Search, Route.VacancyList),
    FAVORITE("Favorite", Icons.Filled.FavoriteBorder, Route.Favorite),
    RESPONSE("Response", Icons.Filled.Email, Route.Response),
    MESSAGES("Messages", Icons.Filled.Notifications, Route.Messages),
    PROFILE("Profile", Icons.Filled.Person, Route.Profile)
}

//data class BottomNavigationItem(
//    val title: String,
//    val selectedIcon: ImageVector,
//    val unselectedIcon: ImageVector,
//    val hasNews: Boolean,
//    val badgeCount: Int? = null
//)
//
//
//

//sealed class BottomNavScreen (
//    val name : String,
//    val route : String,
//    val selectedIcon: ImageVector,
//    val unselectedIcon: ImageVector,
//    val hasNews: Boolean,
//    val badgeCount: Int? = null,
//)
//){
//
//    object Home : BottomNavScreen(
//        name = "Поиск",
//        route= "home",
//        selectedIcon = Icons.Filled.Home,
//        unselectedIcon = Icons.Outlined.Home,
//        hasNews =true,
//        badgeCount = null
//    )
//    object Favorite : BottomNavScreen(
//        name = "Избраное",
//        route= "Favorite",
//        selectedIcon = Icons.Filled.Email,
//        unselectedIcon = Icons.Outlined.Email,
//        hasNews = false,
//        badgeCount = 9
//    )
//    object Response : BottomNavScreen(
//        name = "Отклики",
//        route= "Response",
//        selectedIcon = Icons.Filled.Settings,
//        unselectedIcon = Icons.Outlined.Settings,
//        hasNews = true,
//        badgeCount = null
//    )
//    object Messages : BottomNavScreen(
//        name = "Отклики",
//        route= "Messages",
//        selectedIcon = Icons.Filled.Settings,
//        unselectedIcon = Icons.Outlined.Settings,
//        hasNews = true,
//        badgeCount = null
//    )
//    object Profile : BottomNavScreen(
//        name = "Профиль",
//        route= "Profile",
//        selectedIcon = Icons.Filled.Settings,
//        unselectedIcon = Icons.Outlined.Settings,
//        hasNews = true,
//        badgeCount = null
//    )
//
