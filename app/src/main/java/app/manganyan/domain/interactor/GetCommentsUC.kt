package app.manganyan.domain.interactor

import android.util.Log
import app.manganyan.common.Resource
import app.manganyan.data.repository.CommentRepository
import app.manganyan.domain.model.Chapter
import app.manganyan.domain.model.Comment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCommentsUC @Inject constructor(
    private val repository: CommentRepository
) {

    operator fun invoke() : Flow<Resource<List<Comment>?>> = flow {
        try {
            emit(Resource.Loading())

            val comments = repository.getComment()

            Log.d("HELLLLLOOOOOO", comments.toString())
            emit(Resource.Success(comments))
        } catch (e: Exception) {
            Log.e("ERROR", "Error with ${e.localizedMessage}")
            emit(Resource.Error(message = "Error with ${e.localizedMessage}"))
        }
    }

}