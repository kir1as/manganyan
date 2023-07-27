package app.manganyan.presentation.screens.comment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.manganyan.common.Resource
import app.manganyan.domain.repository.AuthRepository
import app.manganyan.domain.repository.CommentRepository
import app.manganyan.domain.interactor.CommentInteractor
import app.manganyan.domain.model.Comment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class CommentViewModel @Inject constructor(
    private val repository: CommentRepository,
    private val authRepository: AuthRepository,
    private val interactor: CommentInteractor,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _commentState = MutableStateFlow(CommentState())
    val state = _commentState.asStateFlow()

    val chapterId: String = checkNotNull(savedStateHandle["chapterId"])
    lateinit var idUser : String
    init {
        viewModelScope.launch {
            idUser = getCurrentUser()
        }
        getComment()
    }


    fun postComment(comment: Comment) = viewModelScope.launch {
        repository.postComment(comment).collect{
            result -> when(result){

                is Resource.Success -> _commentState.update{
                    it.copy(
                        isLoading = false,
                        isSuccess = "Commentaire Ajouté",
                        isError = "",
                    )
                }
                is Resource.Loading -> _commentState.update {
                    it.copy(
                        isLoading = true,
                        isSuccess = "",
                        comments = emptyList(),
                        isError = ""
                    )
                }
                is Resource.Error -> _commentState.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = "",
                        comments = emptyList(),
                        isError = result.message ?: "An unexpected error occurred"
                    )
                }
            }
        }
        getComment()
    }

    fun getComment() = viewModelScope.launch {
        interactor.getCommentsUC().onEach { resource ->
            when(resource){
                is Resource.Error ->  _commentState.update {
                    it.copy(
                        isLoading = false,
                        isError = resource.message ?: "An unexpected error occurred",
                        comments = emptyList()
                    )
                }
                is Resource.Loading -> _commentState.update {
                    it.copy(
                        isLoading = true,
                        isError = "",
                        comments = emptyList()
                    )
                }
                is Resource.Success -> _commentState.update {
                    var comments : List<Comment> = emptyList()
                    if(resource.data != null){
                         comments = resource.data.filter{ comment -> chapterId == comment.chapterId }
                    }
                    it.copy(
                        isLoading = false,
                        isError = "",
                        comments = comments
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getCurrentUser() : String {
        return authRepository.getCurrentUserId()
    }


}