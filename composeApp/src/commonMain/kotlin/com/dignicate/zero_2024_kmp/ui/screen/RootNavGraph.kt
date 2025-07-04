package com.dignicate.zero_2024_kmp.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dignicate.zero_2024_kmp.ui.screen.home.Graph
import com.dignicate.zero_2024_kmp.ui.screen.home.HomeDetailScreen
import com.dignicate.zero_2024_kmp.ui.screen.home.HomeScreen
import com.dignicate.zero_2024_kmp.ui.screen.home.Routes
import com.dignicate.zero_2024_kmp.ui.screen.home.SettingDetailScreen
import com.dignicate.zero_2024_kmp.ui.screen.home.SettingScreen

@Composable
fun RootNavGraph(
    rootNavController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = rootNavController,
        startDestination = Graph.NAVIGATION_BAR_SCREEN_GRAPH,
    ) {
        mainNavGraph(rootNavController = rootNavController, innerPadding = innerPadding)
        composable(
            route = Routes.HomeDetail.route,
        ) {
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("name")?.let { name ->
                HomeDetailScreen(rootNavController = rootNavController, name = name)
            }
        }
        composable(
            route = Routes.SettingDetail.route,
        ) {
            SettingDetailScreen(rootNavController = rootNavController)
        }
    }
}

fun NavGraphBuilder.mainNavGraph(
    rootNavController: NavHostController,
    innerPadding: PaddingValues
) {
    navigation(
        startDestination = Routes.Home.route,
        route = Graph.NAVIGATION_BAR_SCREEN_GRAPH
    ) {
        composable(route = Routes.Home.route) {
            HomeScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable(route = Routes.Setting.route) {
            SettingScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable(route = Routes.Tab.route) {
            TabScreen(
                rootNavController = rootNavController,
                paddingValues = innerPadding,
            )
        }
    }
}
