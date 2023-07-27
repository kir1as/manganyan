package app.manganyan.presentation.screens.search

import android.provider.CalendarContract.Events
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.manganyan.common.Resource
import app.manganyan.domain.interactor.MangaGetFilterUseCase
import app.manganyan.domain.interactor.MangaGetUseCase
import app.manganyan.domain.model.MangaData
import app.manganyan.presentation.screens.search.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mangaUseCase: MangaGetUseCase,
    private val mangaFilterUseCase: MangaGetFilterUseCase
) : ViewModel(){

    private val stateManga = MutableStateFlow(HomeState())
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

    private fun getMangaList() {
        mangaUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { it1 -> logMangaList(it1) }
                    stateManga.value = HomeState(mangaList = it.data ?: emptyList())
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

    fun getMangaFilterList(filter: String) {
        mangaFilterUseCase(filter).onEach {
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

    fun onSearchChanged(event: String) {
        getMangaFilterList(event)
    }
}