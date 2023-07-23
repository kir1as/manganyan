package app.manganyan.presentation.comment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.manganyan.common.Resource
import app.manganyan.data.repository.CommentRepository
import app.manganyan.domain.model.Comment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class CommentViewModel @Inject constructor(
    private val repository: CommentRepository
): ViewModel() {

    val _commentState = Channel<CommentState>()
    val commentState = _commentState.receiveAsFlow()

    fun postComment(comment: Comment) = viewModelScope.launch {
        repository.postComment(comment).collect{
            result -> when(result){
                is Resource.Success -> {
                    _commentState.send(CommentState(isSuccess = "Commentaire Ajouté"))

                }
                is Resource.Loading -> {
                    _commentState.send(CommentState(isLoading = true))

                }
                is Resource.Error -> {
                    _commentState.send(CommentState(isError = result.message))

                }
            }
        }
    }

    fun getComment() = viewModelScope.launch {
        val comments = repository.getComment()
    }


}