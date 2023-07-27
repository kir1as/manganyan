package app.manganyan.presentation.navigation

sealed class Screens(val route: String) {
    object SignInScreen : Screens(route = "SignIn_Screen")
    object SignUpScreen : Screens(route = "SignUp_Screen")
    object HomeScreen : Screens(route = "Home_Screen")

    object MainScreen : Screens(route = "Main_Screen")
    object MangaDetailScreen : Screens(route = "Manga_Detail")
    object MangaPageScreen : Screens(route = "Manga_Page")

}