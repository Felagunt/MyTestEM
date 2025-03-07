package com.example.mytestem.Route

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavScreen (
    val name : String,
    val route : String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
)