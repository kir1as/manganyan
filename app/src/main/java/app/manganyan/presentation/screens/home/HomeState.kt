package app.manganyan.presentation.screens.home

import app.manganyan.domain.model.MangaData

data class HomeState (
    val isLoading: Boolean = false,
    val mangaList: List<MangaData> = emptyList(),
    val favoriteMangaIds: List<String> = emptyList(),
    val error: String = ""
) {
}
