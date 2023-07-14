package app.manganyan.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.manganyan.presentation.screens.login_screen.SignInScreen
import app.manganyan.presentation.screens.signup_screen.SignUpScreen
import dagger.hilt.android.lifecycle.HiltViewModel

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
            SignUpScreen()
        }
    }

}