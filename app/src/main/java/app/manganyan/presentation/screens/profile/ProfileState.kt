package app.manganyan.presentation.screens.home
import app.manganyan.domain.model.UserApp

data class ProfileState (
    val isLoading: Boolean = false,
    val user: UserApp? = null,
    val error: String = ""
)

