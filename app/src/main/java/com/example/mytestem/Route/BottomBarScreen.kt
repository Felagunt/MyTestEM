package com.example.mytestem.Route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val name: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
) {

    object Home : BottomBarScreen(
        name = "Поиск",
        route = "home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = true,
        badgeCount = null
    )

    object Favorite : BottomBarScreen(
        name = "Избраное",
        route = "Favorite",
        selectedIcon = Icons.Filled.Email,
        unselectedIcon = Icons.Outlined.Email,
        hasNews = false,
        badgeCount = 9
    )

    object Response : BottomBarScreen(
        name = "Отклики",
        route = "Response",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        hasNews = true,
        badgeCount = null
    )

    object Messages : BottomBarScreen(
        name = "Отклики",
        route = "Messages",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        hasNews = true,
        badgeCount = null
    )

    object Profile : BottomBarScreen(
        name = "Профиль",
        route = "Profile",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        hasNews = true,
        badgeCount = null
    )
}