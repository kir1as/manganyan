package app.manganyan.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Colors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.manganyan.domain.model.MangaData

@Composable
fun MangaItemScreen(
    manga: MangaData,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text="${manga.title}",
            overflow = TextOverflow.Ellipsis,
            color= Color.Red
        )
        Text(text="Description : ${manga.description}",
            overflow = TextOverflow.Ellipsis)

       /* Text(text="TrailerUrl : ${manga.cover_id}",
            overflow = TextOverflow.Ellipsis)*/
    }

}