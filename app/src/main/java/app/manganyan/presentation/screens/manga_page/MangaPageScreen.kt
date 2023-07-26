package app.manganyan.presentation.screens.manga_page

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun MangaPageScreen(
    viewModel: MangaPageViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    var appBarVisible by remember { mutableStateOf(true) }

    val coroutineScope = rememberCoroutineScope()

    // LaunchedEffect pour détecter les tap et les interactions de défilement
    LaunchedEffect(appBarVisible) {
        coroutineScope.launch {
            snapshotFlow { appBarVisible }
                .distinctUntilChanged()
                .collect { isVisible ->
                    if (isVisible) {
                        // Attendez 3 secondes avant de masquer la TopAppBar après une interaction
                        delay(3000)
                        appBarVisible = false
                    }
                }
        }
    }

    Scaffold(
        topBar = {
            MangaTopAppBar(
                title = "Mon titre a changer",
                onBackClick = { /*TODO*/ },
                appBarVisible = appBarVisible
            )
        },

        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { tapOffset ->
                    appBarVisible = true
                }
            }

    ) { contentPadding ->
        MangaPageScreenContent(
            uiState = state.value,
            modifier = Modifier
                .padding(contentPadding)
        )
    }
}

@Composable
fun MangaPageScreenContent(
    uiState: UiState,
    modifier: Modifier
) {
    when {
        uiState.isLoading -> {
            Text("Loading...")
        }

        uiState.error.isNotEmpty() -> {
            Text("Error: ${uiState.error}")
        }

        uiState.chapter.data.isEmpty() -> {
            Text("No data")
        }

        else -> {
            DisplayMangaImageList(
                imageUrls = uiState.chapter.dataSaver,
                hash = uiState.chapter.hash
            )
        }
    }

}

@Composable
fun DisplayMangaImageList(
    imageUrls: List<String>,
    hash: String,
) {
    LazyColumn() {
        items(imageUrls) { imageUrl ->
            val url = "https://uploads.mangadex.org/data-saver/$hash/$imageUrl"
            Log.d("test url ", "url: $url")
            ImageItem(url)
        }
    }
}

@Composable
fun ImageItem(
    imageUrl: String
) {
    Image(
        painter = rememberAsyncImagePainter(
            imageUrl,
            onLoading = {
                Log.d("Image Loading", "Loading")
            },
            onError = { error ->
                Log.e("Image Loading Error", "Error: $error")
            },
            contentScale = ContentScale.Crop,
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 1.dp)
    )
}

@Composable
fun MangaTopAppBar(
    title: String,
    onBackClick: () -> Unit,
    appBarVisible: Boolean
) {
    AnimatedVisibility(visible = appBarVisible) {

        TopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            },
        )
    }


}