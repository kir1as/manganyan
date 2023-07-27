package app.manganyan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import app.manganyan.MainScreen
import app.manganyan.presentation.screens.favorites.FavoriteScreen
import app.manganyan.presentation.screens.home.HomeScreen
import app.manganyan.presentation.screens.manga_page.MangaPageScreen
import app.manganyan.presentation.screens.profile.ProfileScreen
import app.manganyan.presentation.screens.manga_detail.MangaDetailScreen
import app.manganyan.presentation.screens.search.SearchScreen

@Composable
fun BottomNavGraph(navController: NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.HomeScreen.route)
    {
        composable(route = BottomBarScreen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = BottomBarScreen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(route = BottomBarScreen.ProfileScreen.route) {
            ProfileScreen()
        }
        composable(route = BottomBarScreen.FavoriteScreen.route) {
            MangaPageScreen(navController = navController)
        }
        composable(route = Screens.MainScreen.route) {
            MainScreen()
        }
        composable(
            route = Screens.MangaDetailScreen.route + "/{mangaId}",
            arguments = listOf(navArgument("mangaId") { type = NavType.StringType })
        ) {
            MangaDetailScreen(navController = navController)
        }
    }

}