package app.manganyan.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import app.manganyan.R

import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import app.manganyan.presentation.navigation.Screens
import app.manganyan.presentation.screens.search.HomeViewModel


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavHostController) {

    val state by viewModel.state.collectAsState()

    Column {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Manganyan",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            maxLines = 1,
            modifier = Modifier.padding(20.dp)
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
                            .aspectRatio(2 / 3f)
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.qrcode),
                        contentDescription = null,
                        modifier = Modifier
                            .height(150.dp)
                            .width(100.dp)
                            .aspectRatio(2 / 3f)
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = mangaTitle ?: "Unknown Title",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    if (mangaDesc != null) {
                        Text(
                            text = mangaDesc,
                            fontSize = 14.sp,
                            color = Color.Black,
                            maxLines = 2
                        )
                    }
                }
            }
        }
    }
}





