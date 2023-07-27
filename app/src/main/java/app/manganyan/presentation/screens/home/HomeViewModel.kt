// HomeViewModel.kt
package app.manganyan.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.manganyan.common.Resource
import app.manganyan.domain.interactor.MangaGetFilterUseCase
import app.manganyan.domain.interactor.MangaGetUseCase
import app.manganyan.domain.model.MangaData
import app.manganyan.presentation.screens.search.MangaDetailState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mangaUseCase: MangaGetUseCase,
    private val mangaFilterUseCase: MangaGetFilterUseCase
) : ViewModel() {

    private val stateManga = MutableStateFlow(HomeState())
    val state = stateManga.asStateFlow()
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        Log.d("tag", "testing message")
        val userUid = FirebaseAuth.getInstance().uid as String
        getFavoriteMangaList(userUid)
        getMangaList()
    }

    fun logMangaList(mangaList: List<MangaData>) {
        for (manga in mangaList) {
            Log.d("MangaDTO", "ID: ${manga.id}")
            Log.d("MangaDTO", "Title: ${manga.title}")
            Log.d("MangaDTO", "Cover ID: ${manga.cover_id}")
            Log.d("MangaDTO", "Description: ${manga.description}")
            Log.d("MangaDTO", "cover: ${manga.image}")
            Log.d("MangaDTO", "author: ${manga.author}")
        }
    }
     fun addMangaToFavorites(firebaseFirestore: FirebaseFirestore, userUid: String, mangaId: String) {
        val userRef = firebaseFirestore.collection("users").document(userUid)
        userRef.update("favoris", FieldValue.arrayUnion(mangaId))
            .addOnSuccessListener {
                println("Manga ajouté aux favoris avec succès.")
                // Update the state to reflect the change in favorites
                stateManga.value = stateManga.value.copy(favoriteMangaIds = stateManga.value.favoriteMangaIds + mangaId)
            }
            .addOnFailureListener { e ->
                println("Erreur lors de l'ajout du manga aux favoris : $e")
            }
    }


     fun removeMangaFromFavorites(firebaseFirestore: FirebaseFirestore, userUid: String, mangaId: String) {
        val userRef = firebaseFirestore.collection("users").document(userUid)
        userRef.update("favoris", FieldValue.arrayRemove(mangaId))
            .addOnSuccessListener {
                println("Manga supprimé des favoris avec succès.")
                // Update the state to reflect the change in favorites
                stateManga.value = stateManga.value.copy(favoriteMangaIds = stateManga.value.favoriteMangaIds - mangaId)
            }
            .addOnFailureListener { e ->
                println("Erreur lors de la suppression du manga des favoris : $e")
            }
    }


     fun getFavoriteMangaList(userUid: String) {
        val firestore = FirebaseFirestore.getInstance()

        stateManga.value = HomeState(isLoading = true)

        firestore.collection("users").document(userUid)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    stateManga.value = HomeState(error = "Error fetching favorites")
                    return@addSnapshotListener
                }

                val favoriteMangaList = snapshot?.get("favoris") as? List<String>
                stateManga.value = stateManga.value.copy(favoriteMangaIds = favoriteMangaList ?: emptyList())
            }
    }


     fun filterMangaList(mangaList: List<MangaData>, searchQuery: String): List<MangaData> {
        return if (searchQuery.isEmpty()) {
            mangaList
        } else {
            mangaList.filter { manga ->
                manga.title?.contains(searchQuery, ignoreCase = true) == true ||
                        manga.description?.contains(searchQuery, ignoreCase = true) == true
            }
        }
    }

    private fun getMangaList() {
        mangaUseCase().onEach {
            when (it) {

                is Resource.Success -> stateManga.update { it1 ->
                    it1.copy(
                        isLoading = false,
                        mangaList = it.data ?: emptyList() ,
                        error = ""
                    )
                }
                is Resource.Loading -> {
                    stateManga.value = HomeState(isLoading = true)
                }
                is Resource.Error -> {
                    stateManga.value = HomeState(error = it.message ?: "An Unexpected Error")
                }
            }
        }.launchIn(viewModelScope)
    }



    fun getMangaFilterList(filter: String) {
        mangaFilterUseCase(filter).onEach {
            when (it) {

                is Resource.Success -> stateManga.update { it1 ->
                    it1.copy(
                        isLoading = false,
                        mangaList = it.data ?: emptyList() ,
                        error = ""
                    )
                }
                is Resource.Loading -> {
                    stateManga.value = HomeState(isLoading = true)
                }
                is Resource.Error -> {
                    stateManga.value = HomeState(error = it.message ?: "An Unexpected Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onSearchChangedList(event: String) {
        getMangaFilterList(event)
    }

}
