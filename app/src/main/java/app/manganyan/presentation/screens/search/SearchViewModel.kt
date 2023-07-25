package app.manganyan.presentation.screens.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.manganyan.common.Resource
import app.manganyan.domain.interactor.MangaGetUseCase
import app.manganyan.domain.model.MangaData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mangaUseCase: MangaGetUseCase
) : ViewModel(){

    private val stateManga = MutableStateFlow(SearchState())
    val state = stateManga.asStateFlow()

    init {
        Log.d("tag", "testing message")
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

    fun getMangaList() {
        mangaUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { it1 -> logMangaList(it1) }
                    stateManga.value = SearchState(mangaList = it.data ?: emptyList())
                }
                is Resource.Loading -> {
                    stateManga.value = SearchState(isLoading = true)
                }
                is Resource.Error -> {
                    stateManga.value = SearchState(error = it.message ?: "An Unexpected Error")
                }
            }
        }.launchIn(viewModelScope)
    }
}