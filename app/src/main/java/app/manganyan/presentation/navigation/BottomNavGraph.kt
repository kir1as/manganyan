package app.manganyan.presentation.navigation

import FavoriteScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import app.manganyan.MainScreen
import app.manganyan.presentation.screens.comment.CommentScreen
import app.manganyan.presentation.screens.home.HomeScreen
import app.manganyan.presentation.screens.manga_page.MangaPageScreen
import app.manganyan.presentation.screens.profile.ProfileScreen
import app.manganyan.presentation.screens.manga_detail.MangaDetailScreen
import app.manganyan.presentation.screens.search.SearchScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun BottomNavGraph(navController: NavHostController, firebaseInstance: FirebaseDatabase
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
            FirebaseAuth.getInstance().currentUser?.uid?.let { it1 -> FavoriteScreen( userUid = it1) }
        }
        composable(route = Screens.MainScreen.route) {
            MainScreen()
        }
        composable(
            route = Screens.MangaPageScreen.route + "/{chapterId}",
            arguments = listOf(navArgument("chapterId") { type = NavType.StringType })
        ) {
            MangaPageScreen(navController = navController)
        }
        composable(
            route = Screens.MangaDetailScreen.route + "/{mangaId}",
            arguments = listOf(navArgument("mangaId") { type = NavType.StringType })
        ) {
            MangaDetailScreen(navController = navController)
        }
    }

}