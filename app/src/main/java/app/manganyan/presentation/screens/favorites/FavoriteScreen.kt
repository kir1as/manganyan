import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.collect

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FavoriteScreen(
     // Callback added here
    viewModel: FavoriteViewModel = viewModel()
) {
    // Load favorite manga ids when the screen is displayed
    viewModel.loadFavoriteMangaIds(FirebaseAuth.getInstance().uid!!)


    val state = viewModel.state.value

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            Text(
                text = "Loading...",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )
        } else {
            if (state.error.isNotBlank()) {
                Text(
                    text = "Error: ${state.error}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            } else {
                // Don't display anything here since we're using the callback to pass the data
            }
        }
    }
}

@Composable
fun FavoriteList(favoriteMangaIds: List<String>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(favoriteMangaIds) { mangaId ->
            Text(
                text = mangaId,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )
        }
    }
}
