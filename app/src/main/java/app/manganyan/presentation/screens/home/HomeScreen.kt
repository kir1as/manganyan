package app.manganyan.presentation.screens.home

import androidx.compose.foundation.Image
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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import app.manganyan.domain.model.MangaData
import app.manganyan.presentation.navigation.Screens
import app.manganyan.presentation.screens.search.HomeViewModel


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavHostController) {

    val state by viewModel.state.collectAsState()

    Column {
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
        LazyColumn {


            items(state.mangaList) { manga ->
                MangaCard(
                    mangaTitle = manga.title,
                    mangaId = manga.id,
                    mangaCover = manga.image,
                    mangaDesc = manga.description,
                    navController
                )
            }
        }
    }
}



@Composable
fun MangaCard(mangaTitle: String?, mangaId: String?, mangaCover: String?, mangaDesc: String?, navController: NavHostController) {
    Column {

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable {
                    mangaId?.let {
                        navController.navigate(Screens.MangaDetailScreen.route + "/${mangaId}")
                    }
                }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (mangaCover != null && mangaId != null) {
                    val imageUrl = "https://mangadex.org/covers/$mangaId/$mangaCover"
                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .height(150.dp)
                            .width(100.dp)
                            .aspectRatio(2 / 3f) // To maintain the aspect ratio of the image
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.qrcode), // Replace with your desired placeholder image
                        contentDescription = null,
                        modifier = Modifier
                            .height(150.dp)
                            .width(100.dp)
                            .aspectRatio(2 / 3f) // To maintain the aspect ratio of the image
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f) // To make the title and description take up the remaining space
                ) {
                    Text(
                        text = mangaTitle ?: "Unknown Title",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    if (mangaDesc != null) {
                        Text(
                            text = mangaDesc, // Replace this with the actual description text
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.secondary,
                            maxLines = 2 // Limit the description to two lines
                        )
                    }
                }
            }
        }
    }
}





