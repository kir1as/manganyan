package app.manganyan.presentation.screens.search

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.manganyan.common.Resource
import app.manganyan.domain.interactor.MangaGetByIdUseCase
import app.manganyan.domain.model.MangaDetailData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MangaDetailViewModel @Inject constructor(
    private val mangaGetByIdUseCase: MangaGetByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _stateManga = MutableStateFlow(MangaDetailState())
    val state = _stateManga.asStateFlow()

    private val mangaId: String = checkNotNull(savedStateHandle["mangaId"])

    init {
        getMangaById(mangaId)
    }

    fun logMangaList(manga: MangaDetailData) {

        Log.d("MangaDTO", "ID v2: ${manga.id}")
        Log.d("MangaDTO", "Title: ${manga.title}")
        Log.d("MangaDTO", "Cover ID v2: ${manga.cover_id}")
        Log.d("MangaDTO", "Description: ${manga.description}")
        Log.d("MangaDTO", "cover: ${manga.image}")
        Log.d("MangaDTO", "author: ${manga.author}")
    }

    fun getMangaById(mangaId: String) {
        mangaGetByIdUseCase(mangaId = mangaId).onEach {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { it1 -> logMangaList(it1) }
                    _stateManga.value = MangaDetailState(manga = it.data ?: null)
                }
                is Resource.Loading -> {
                    _stateManga.value = MangaDetailState(isLoading = true)
                }
                is Resource.Error -> {
                    _stateManga.value = MangaDetailState(error = it.message ?: "An Unexpected Error")
                }
            }
        }.launchIn(viewModelScope)
    }
}