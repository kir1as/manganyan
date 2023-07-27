package app.manganyan.presentation.screens.manga_page

import app.manganyan.domain.model.Chapter
import app.manganyan.domain.model.Chapters

data class UiState (
    val isLoading: Boolean = false,
    val chapter: Chapter = Chapter(hash = "", data = emptyList(), dataSaver = emptyList()),
    val error: String = ""
)