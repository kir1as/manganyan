package app.manganyan

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.manganyan.presentation.navigation.BottomNavGraph
import app.manganyan.presentation.navigation.BottomBarScreen
import app.manganyan.presentation.ui.theme.BlueYellowGradient
import app.manganyan.presentation.ui.theme.YellowApp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)




@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController)
    }
}




@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        BottomBarScreen.HomeScreen,
        BottomBarScreen.FavoriteScreen,
        BottomBarScreen.ProfileScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Box(
        modifier = Modifier.background(BlueYellowGradient)
    ) {
        BottomNavigation(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }

}

@Composable
fun RowScope.AddItem (
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    //var selectedTabIndex by remember { mutableStateOf(0) }

    BottomNavigationItem(
        label = {
            Text(text = screen.title, color = if(currentDestination?.hierarchy?.any {
                    it.route == screen.route
                } == true) YellowApp else Color.Black)
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "Navigation Icon" , tint = if(currentDestination?.hierarchy?.any {
                    it.route == screen.route
                } == true) YellowApp else Color.Black)
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,

        onClick = {
            navController.navigate(screen.route)
        }
    )

}