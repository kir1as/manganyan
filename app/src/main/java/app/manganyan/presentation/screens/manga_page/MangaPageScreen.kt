package app.manganyan.presentation.screens.manga_page

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.manganyan.R
import coil.compose.rememberImagePainter

@Composable
fun MangaPageScreen(
    viewModel: MangaPageViewModel = hiltViewModel()
){
    val state = viewModel.state.collectAsStateWithLifecycle()

    when{
        state.value.isLoading -> {

        }
        state.value.error.isNotEmpty() -> {

        }
        else -> {
            DisplayMangaImageList(state.value.chapter.data)
        }

    }
}

@Composable
fun DisplayMangaImageList(
    imageUrls: List<String>
){
    LazyColumn{
        items(imageUrls) { imageUrl ->
            ImageItem(imageUrl)
        }
    }
}

@Composable
fun ImageItem(
    imageUrl: String
){


}