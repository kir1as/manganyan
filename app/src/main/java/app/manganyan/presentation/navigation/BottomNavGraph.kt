package app.manganyan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.manganyan.MainScreen
import app.manganyan.presentation.screens.favorites.FavoriteScreen
import app.manganyan.presentation.screens.home.HomeScreen
import app.manganyan.presentation.screens.manga_page.MangaPageScreen
import app.manganyan.presentation.screens.profile.ProfileScreen
import app.manganyan.presentation.screens.search.SearchScreen

@Composable
fun BottomNavGraph(navController: NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.HomeScreen.route)
    {
        composable(route = BottomBarScreen.HomeScreen.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.SearchScreen.route) {
            SearchScreen()
        }
        composable(route = BottomBarScreen.ProfileScreen.route) {
            ProfileScreen()
        }
        composable(route = BottomBarScreen.FavoriteScreen.route) {
            MangaPageScreen()
        }
        composable(route = Screens.MainScreen.route) {
            MainScreen()
        }
    }

}