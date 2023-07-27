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


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavHostController) {

    val state by viewModel.state.collectAsState()



    LazyColumn {
        items(state.mangaList) { manga ->
            MangaCard(mangaTitle = manga.title, mangaId = manga.id, mangaCover = manga.image, navController)
        }
    }
}



@Composable
fun MangaCard(mangaTitle: String?, mangaId: String?, mangaCover: String?, navController: NavHostController) {
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
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            if (mangaCover != null && mangaId != null) {
                val imageUrl = "https://mangadex.org/covers/$mangaId/$mangaCover"
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.qrcode), // Replace with your desired placeholder image
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = mangaTitle ?: "Unknown Title",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )
        }
    }
}
