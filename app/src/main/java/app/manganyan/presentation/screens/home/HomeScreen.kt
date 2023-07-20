package app.manganyan.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

@Composable
fun HomeScreen(
) {
    Image(
        painter = rememberAsyncImagePainter("https://mangadex.org/covers/cfc3d743-bd89-48e2-991f-63e680cc4edf/275c97c9-a612-4311-a30c-7194f44a287d.jpg.512.jpg"),
        contentDescription = null,
        modifier = Modifier.size(128.dp)
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Welcome to Manganyan",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary
        )
    }
}
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}