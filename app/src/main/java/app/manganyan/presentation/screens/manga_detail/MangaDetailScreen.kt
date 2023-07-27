package app.manganyan.presentation.screens.manga_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import app.manganyan.R
import app.manganyan.presentation.navigation.Screens
import app.manganyan.presentation.screens.search.MangaDetailViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun MangaDetailScreen(
    viewModel: MangaDetailViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by viewModel.state.collectAsState()


    Column(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                CircularProgressIndicator()
            }
        } else if (state.error.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                Text("Error: ${state.error}", modifier = Modifier.padding(8.dp))
            }
        } else {
            MangaDetailHeader(
                mangaTitle = state.manga?.title,
                mangaId = state.manga?.id,
                mangaCover = state.manga?.image,
                author = state.manga?.author,
                onBackClick = { navController.popBackStack() },
            )
            MangaDetailContent(description = state.manga?.description, chapters = state.chapters, navController = navController)
        }


    }
}

@Composable
fun MangaDetailHeader(
    mangaTitle: String?,
    mangaId: String?,
    mangaCover: String?,
    author: String?,
    onBackClick: () -> Unit
) {
    Column(
    ) {
        if (mangaCover != null && mangaId != null) {
            val imageUrl = "https://mangadex.org/covers/$mangaId/$mangaCover"
            Image(
                painter = rememberAsyncImagePainter(imageUrl, contentScale = ContentScale.Crop),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop,
            )
        } else {
            Image(
                painter = painterResource(R.drawable.qrcode),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        IconButton(onClick = { onBackClick() }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = null)
        }

        Text(
            text = mangaTitle ?: "Unknown Title",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
            )

        Text(
            text = if (author != null) "Author : $author" else "Unknown Author",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun MangaDetailContent(description: String?, chapters: List<String>, navController: NavHostController) {

    var selectedTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SegmentedButton(
                text = "Description",
                onClick = { selectedTab = 0 }
            )
            SegmentedButton(
                text = "Chapters",
                onClick = { selectedTab = 1 }
            )
        }

        if (selectedTab == 0) {
            MangaDetailDescription(description = description)
        } else {
            MangaDetailChapters(chaptersId = chapters, navController = navController)
        }
    }

}

@Composable
fun SegmentedButton(text: String, onClick: () -> Unit) {

    Card(
        modifier = Modifier.clickable { onClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun MangaDetailDescription(description: String?) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = description ?: "No description available.",
                fontSize = 14.sp,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
fun MangaDetailChapters(chaptersId: List<String>, navController: NavHostController) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        itemsIndexed(chaptersId) { index, id ->
            ChapterCard(index = index, chapterNumber = id, navController = navController)
        }
    }

}

@Composable
fun ChapterCard(index: Int, chapterNumber: String, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable {
                navController.navigate(Screens.MangaPageScreen.route + "/${chapterNumber}")
            },
    ) {
        Text(
            text = "chapter $index",
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
        )
    }
}

