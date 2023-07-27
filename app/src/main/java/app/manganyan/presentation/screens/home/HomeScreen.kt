package app.manganyan.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import app.manganyan.R
import app.manganyan.domain.model.MangaData
import app.manganyan.presentation.navigation.Screens

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val state by viewModel.state.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    // Filter the manga list based on the search query
    val filteredMangaList = remember(state.mangaList, searchQuery) {
        viewModel.filterMangaList(state.mangaList, searchQuery)
    }

    Column {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Manganyan",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            maxLines = 1,
            modifier = Modifier.padding(20.dp)
        )
        SearchBar(onChange = {
            viewModel.onSearchChangedList(it)
            //viewModel.onSearchChanged(it)

        }, viewModel)
        LazyColumn {
            items(state.mangaList) { manga ->
                val isFavorite = state.favoriteMangaIds.contains(manga.id)
                Log.d("Hello", isFavorite.toString())
                MangaCard(
                    mangaTitle = manga.title,
                    mangaId = manga.id,
                    mangaCover = manga.image,
                    mangaDesc = manga.description,
                    navController = navController,
                    isFavorite = isFavorite,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun MangaCard(
    mangaTitle: String?,
    mangaId: String?,
    mangaCover: String?,
    mangaDesc: String?,
    navController: NavHostController,
    isFavorite: Boolean,
    viewModel: HomeViewModel

) {

    Log.d("MangaCard", "Manga ID: $mangaId, isFavorite: $isFavorite")

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
                    painter = rememberAsyncImagePainter("https://mangadex.org/covers/227e3f72-863f-46f9-bafe-c43104ca29ee/2fbddeeb-5743-4a36-8341-847b9c597ce2.jpg.512.jpg"),
                    contentDescription = null,
                    modifier = Modifier
                        .height(150.dp)
                        .width(100.dp)
                        .aspectRatio(2 / 3f)
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = mangaTitle ?: "Unknown Title",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
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

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp)
            ) {
                IconButton(
                    onClick = {
                        mangaId?.let { id ->
                            val userUid = FirebaseAuth.getInstance().currentUser?.uid ?: ""
                            if (isFavorite) {
                                viewModel.removeMangaFromFavorites(
                                    FirebaseFirestore.getInstance(),
                                    userUid,
                                    id
                                )
                            } else {
                                viewModel.addMangaToFavorites(
                                    FirebaseFirestore.getInstance(),
                                    userUid,
                                    id
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        imageVector = if (isFavorite) {
                            ImageVector.vectorResource(id = R.drawable.fav_yes) // Red heart icon
                        } else {
                            ImageVector.vectorResource(id = R.drawable.fav_no) // Gray heart icon
                        },
                        contentDescription = "Favorite Icon",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}




@Composable
fun SearchBar(onChange: (String) -> Unit, viewModel: HomeViewModel) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = {
            val userUid = FirebaseAuth.getInstance().uid as String
            viewModel.getFavoriteMangaList(userUid)
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
