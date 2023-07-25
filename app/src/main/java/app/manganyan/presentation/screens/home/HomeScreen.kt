package app.manganyan.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import app.manganyan.R
import androidx.compose.runtime.collectAsState

import app.manganyan.presentation.screens.search.SearchViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import app.manganyan.domain.model.MangaData
import app.manganyan.presentation.screens.search.DisplayMangaList
import app.manganyan.presentation.screens.search.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    var selectedItem by remember { mutableStateOf<String?>(null) } // Set initial value to null

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.weight(1f)) // Spacer to center "Bienvenue" vertically
            Text(
                text = "Bienvenue",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
                maxLines = 1,
                modifier = Modifier.padding(20.dp) // Padding of 20
            )
            app.manganyan.presentation.screens.search.SearchBar(onChange = {
                viewModel.onSearchChanged(it)
            })

            Box(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = selectedItem ?: "All",
                    modifier = Modifier
                        .clickable {
                            selectedItem = if (selectedItem == null) "All" else null
                        }
                )
                DropdownMenu(
                    expanded = selectedItem != null,
                    onDismissRequest = {
                        selectedItem = null
                    }
                ) {
                    DropdownMenuItem(onClick = {
                        selectedItem = "All"
                    }) {
                        Text(text = "All")
                    }
                    DropdownMenuItem(onClick = {
                        selectedItem = "Actions"
                    }) {
                        Text(text = "Actions")
                    }
                    DropdownMenuItem(onClick = {
                        selectedItem = "Hentai"
                    }) {
                        Text(text = "Hentai")
                    }
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.error.isNotEmpty()) {
                Text("Error: ${state.error}", modifier = Modifier.padding(8.dp))
            } else {
                LazyColumn {
                    items(state.mangaList) { manga ->
                        MangaCard(
                            mangaTitle = manga.title,
                            mangaId = manga.id,
                            mangaCover = manga.image,
                            mangaAuthor = manga.author,
                            mangaDesc = manga.description
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f)) // Spacer to center "Bienvenue" vertically
        }
    }
}


    @Composable
    fun MangaCard(
        mangaTitle: String?,
        mangaId: String?,
        mangaCover: String?,
        mangaAuthor: String?,
        mangaDesc: String?
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(160.dp) // Reduced card height
        ) {
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                // Image on the left
                if (mangaCover != null && mangaId != null) {
                    val imageUrl = "https://mangadex.org/covers/$mangaId/$mangaCover"
                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .height(144.dp) // Reduced image height
                            .aspectRatio(2 / 3f) // Adjust the aspect ratio as per your image size
                            .padding(end = 16.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.qrcode), // Replace with your desired placeholder image
                        contentDescription = null,
                        modifier = Modifier
                            .height(144.dp) // Reduced placeholder image height
                            .aspectRatio(2 / 3f) // Adjust the aspect ratio as per your placeholder image size
                            .padding(end = 16.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    // Title
                    Text(
                        text = mangaTitle ?: "Unknown Title",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Author
                    Text(
                        text = mangaAuthor ?: "Unknown Author",
                        fontSize = 15.sp,
                        color = MaterialTheme.colors.secondary,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Description
                    Text(
                        text = mangaDesc ?: "No description available",
                        fontSize = 15.sp,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 2 // Limiting description to 2 lines, adjust as needed
                    )
                }
            }
        }
    }




