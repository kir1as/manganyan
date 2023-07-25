package app.manganyan.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.manganyan.domain.model.MangaData
import app.manganyan.presentation.screens.home.MangaCard

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()


    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(onChange = {
            viewModel.onSearchChanged(it)
        })
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.error.isNotEmpty()) {
            Text("Error: ${state.error}", modifier = Modifier.padding(8.dp))
        } else {
            DisplayMangaList(state.mangaList)
        }


    }
}

@Composable
fun SearchBar(onChange: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = {
            text = it
            onChange(it)
        },
        label = { Text("Search") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            ),
    )
}

@Composable
fun DisplayMangaList(mangaList: List<MangaData>) {
    LazyColumn {
        items(mangaList) { manga ->
            MangaCard(mangaTitle = manga.title, mangaId = manga.id, mangaCover = manga.image)
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}
