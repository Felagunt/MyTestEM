//package com.example.mytestem.Route
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.layout.RowScope
//import androidx.compose.material3.Badge
//import androidx.compose.material3.BadgedBox
//import androidx.compose.material3.BottomAppBar
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.derivedStateOf
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavDestination
//import androidx.navigation.NavDestination.Companion.hierarchy
//import androidx.navigation.NavGraph.Companion.findStartDestination
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//import com.example.mytestem.presentation.favorite.FavoriteScreenRoot
//import com.example.mytestem.presentation.messages.MessagesScreen
//import com.example.mytestem.presentation.profile.ProfileScreen
//import com.example.mytestem.presentation.responses.ResponseScreen
//import com.example.mytestem.presentation.search.MainScreenRoot
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun App() {
//    MaterialTheme {
//
//        val navController = rememberNavController()
//
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
//            ?: BottomNavigation.SEARCH.route::class.qualifiedName.orEmpty()
//
//        val currentRouteTrimmed by remember(currentRoute) {
//            derivedStateOf { currentRoute.substringBefore("?") }
//        }
//
//        var selectedItemIndex by rememberSaveable {
//            mutableStateOf(0)
//        }
//        Scaffold(
//            modifier = Modifier,
//            bottomBar = {
//                BottomAppBar {
//                    BottomNavigation.entries
//                        .filterIndexed { index, navigationItem ->
//
//                            val isSelected by remember(currentRoute) {
//                                //derivedStateOf { c }
//                                derivedStateOf { currentRouteTrimmed == navigationItem.route::class.qualifiedName }
//                            }
//
////                            NavigationBarItem(
////                                selected =  isSelected ,
////                                label = { Text(text = navigationItem.label) },
////                                icon = {
////                                    Icon(
////                                        navigationItem.icon,
////                                        contentDescription = navigationItem.label
////                                    )
////                                },
////                                onClick = {
////                                    navController.navigate(navigationItem.route)
////                                }
////                            )
////                        }
////                }
//                //BottomBar(navController)
//            }
//        ) {
//            RootGraph(navController)
//
////                    ContentScreen(
////                        modifier = Modifier.padding(paddingValues),
////                        selectedItemIndex
////                    )
////                NavHost(
////                    navController = navController,
////                    startDestination = Route.VacancyGraph
////                ) {
////
////                }
//        }
//    }
//}
//
//@Composable
//fun BottomBar(navController: NavHostController) {
//    val screens = listOf(
//        BottomBarScreen.Home,
//        BottomBarScreen.Favorite,
//        BottomBarScreen.Response,
//        BottomBarScreen.Messages,
//        BottomBarScreen.Profile
//    )
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//
//    val bottomBarDestination = screens.any {
//        it.route == currentDestination?.route
//    }
//
//    if (bottomBarDestination) {
//        NavigationBar {
//            screens.forEach { screen ->
//                AddItem(
//                    screen = screen,
//                    currentDestination = currentDestination,
//                    navController = navController
//                )
//            }
//        }
//    }
//
//}
//
//@Composable
//fun RowScope.AddItem(
//    screen: BottomBarScreen,
//    currentDestination: NavDestination?,
//    navController: NavHostController
//) {
//    NavigationBarItem(
//        icon = {
//            BadgedBox(badge = {
//                if (screen.badgeCount!! > 0)
//                    Badge() {
//                        Text(text = screen.badgeCount.toString())
//                    }
//            }) {
//                Icon(
//                    imageVector = screen.selectedIcon,
//                    contentDescription = "icon"
//                )
//            }
//        },
//        label = {
//            Text(text = screen.name)
//        },
//        selected = currentDestination?.hierarchy?.any {
//            it.route == screen.route
//        } == true,
//        onClick = {
//            navController.navigate(screen.route) {
//                popUpTo(navController.graph.findStartDestination().id)
//                launchSingleTop = true
//            }
//        }
//    )
//}
//
//
//@Composable
//fun ContentScreen(
//    modifier: Modifier = Modifier,
//    selectedIndex: Int
//) {
//    when (selectedIndex) {
//        0 -> MainScreenRoot()
//        1 -> FavoriteScreenRoot()
//        2 -> ResponseScreen()
//        3 -> MessagesScreen()
//        4 -> ProfileScreen()
//    }
//}
//
//
////                        NavigationBar {
////                            items.forEachIndexed { index, bottomNavScreen ->
////                                NavigationBarItem(
////                                    selected = selectedItemIndex == index,
////                                    onClick = {
////                                        selectedItemIndex = index
////                                    },
////                                    icon = {
////                                        BadgedBox(badge = {
////                                            if (bottomNavScreen.badgeCount > 0)
////                                                Badge() {
////                                                    Text(text = bottomNavScreen.badgeCount.toString())
////                                                }
////                                        }) {
////                                            Icon(
////                                                imageVector = bottomNavScreen.selectedIcon,
////                                                contentDescription = "icon"
////                                            )
////                                        }
////                                    },
////                                    label = {
////                                        Text(text = bottomNavScreen.name)
////                                    }
////                                )
////                            }
////                        }
//
////val items = listOf(
////            BottomNavScreen(
////                name = "Поиск",
////                route = "home",
////                selectedIcon = Icons.Filled.Home,
////                unselectedIcon = Icons.Outlined.Home,
////                hasNews = true,
////                badgeCount = null
////            ),
////            BottomNavScreen(
////                name = "Избраное",
////                route = "Favorite",
////                selectedIcon = Icons.Filled.Email,
////                unselectedIcon = Icons.Outlined.Email,
////                hasNews = false,
////                badgeCount = 9
////            ),
////            BottomNavScreen(
////                name = "Отклики",
////                route = "Response",
////                selectedIcon = Icons.Filled.Settings,
////                unselectedIcon = Icons.Outlined.Settings,
////                hasNews = true,
////                badgeCount = null
////            ), BottomNavScreen(
////                name = "Отклики",
////                route = "Messages",
////                selectedIcon = Icons.Filled.Settings,
////                unselectedIcon = Icons.Outlined.Settings,
////                hasNews = true,
////                badgeCount = null
////            ), BottomNavScreen(
////                name = "Профиль",
////                route = "Profile",
////                selectedIcon = Icons.Filled.Settings,
////                unselectedIcon = Icons.Outlined.Settings,
////                hasNews = true,
////                badgeCount = null
////            )
////        )