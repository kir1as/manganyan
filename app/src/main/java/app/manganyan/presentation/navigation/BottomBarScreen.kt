package app.manganyan.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object HomeScreen: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object SearchScreen: BottomBarScreen(
        route = "search",
        title = "Search",
        icon = Icons.Default.Search
    )
    object FavoriteScreen: BottomBarScreen(
        route = "favorite",
        title = "Favorite",
        icon = Icons.Default.Favorite
    )
    object ProfileScreen: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

}