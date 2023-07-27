import androidx.lifecycle.ViewModel
import app.manganyan.presentation.screens.favorites.FavoriteState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoriteViewModel : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state: StateFlow<FavoriteState> get() = _state

    data class User(
        val favoris: List<String>?
    )

    fun loadFavoriteMangaIds(userId: String) {

    }
}
