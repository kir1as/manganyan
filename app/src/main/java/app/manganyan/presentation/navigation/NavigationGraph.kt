package app.manganyan.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.manganyan.MainScreen
import app.manganyan.presentation.screens.home.HomeScreen
import app.manganyan.presentation.screens.login_screen.SignInScreen
import app.manganyan.presentation.screens.manga_detail.MangaDetailScreen
import app.manganyan.presentation.screens.signup_screen.SignUpScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SignInScreen.route
    ) {
        composable(route = Screens.SignInScreen.route) {
            SignInScreen(navController = navController)
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navController = navController)
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