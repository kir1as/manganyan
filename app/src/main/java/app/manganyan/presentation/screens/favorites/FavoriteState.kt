package app.manganyan.presentation.screens.favorites

import app.manganyan.domain.model.MangaData

data class FavoriteState (
    val isLoading: Boolean = false,
    val favoriteMangaIds: List<String> = emptyList(),
    val error: String = ""
) {
}
