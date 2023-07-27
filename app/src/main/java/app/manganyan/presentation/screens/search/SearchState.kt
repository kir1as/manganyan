package app.manganyan.presentation.screens.search

import app.manganyan.domain.model.MangaData

data class SearchState (
    val isLoading: Boolean = false,
    val mangaList: List<MangaData> = emptyList(),
    val error: String = ""
)
