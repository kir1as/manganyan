package app.manganyan.presentation.screens.home

import android.provider.CalendarContract.Events
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.manganyan.common.Resource
import app.manganyan.domain.interactor.MangaGetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mangaUseCase: MangaGetUseCase
) : ViewModel(){

    private val stateManga = mutableStateOf(HomeState())
    val state : State<HomeState> = stateManga

    init {

        getMangaList()
    }

    private fun getMangaList() {
        mangaUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    stateManga.value = HomeState(mangaList = it.data ?: emptyList())
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
}