package app.manganyan.presentation.screens.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.manganyan.domain.model.MangaData

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            Text("Loading...")
        }
        state.error.isNotEmpty() -> {
            Text("Error: ${state.error}")
        }
        else -> {
            DisplayMangaList(state.mangaList)
        }
    }
}

@Composable
fun DisplayMangaList(mangaList: List<MangaData>) {
    LazyColumn {
        items(mangaList) { manga ->
            manga.title?.let { Text(text = it) }
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}