package app.manganyan.presentation.screens.search

import app.manganyan.domain.model.MangaDetailData

data class MangaDetailState (
    val isLoading: Boolean = false,
    val manga: MangaDetailData? = null,
    val error: String = ""
)
