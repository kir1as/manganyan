package app.manganyan.presentation.screens.manga_page

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.manganyan.common.Resource
import app.manganyan.domain.interactor.MangaInteractor
import app.manganyan.domain.model.Chapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class MangaPageViewModel @Inject constructor(
    private val interactor: MangaInteractor
) : ViewModel(){

    private val stateManga = MutableStateFlow(UiState())
    val state = stateManga.asStateFlow()

    init {
        Log.d("tag", "testing message")
        getMangaPage()
    }
    private fun getMangaPage() {
        interactor.getMangaPageUC("1579eff1-2de7-428e-8ff4-7231f1b55c3b").onEach { resource ->
            when(resource){
                is Resource.Error -> stateManga.update {
                    it.copy(
                        isLoading = false,
                        chapter = Chapter(hash = "", data = emptyList(), dataSaver = emptyList()),
                        error = resource.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> stateManga.update {
                    it.copy(
                        isLoading = true,
                        chapter = Chapter(hash = "", data = emptyList(), dataSaver = emptyList()),
                        error = ""
                    )
                }
                is Resource.Success -> stateManga.update {
                    it.copy(
                        isLoading = false,
                        chapter = resource.data ?: Chapter(hash = "", data = emptyList(), dataSaver = emptyList()),
                        error = ""
                    )
                }
            }

        }.launchIn(viewModelScope)
    }
}



