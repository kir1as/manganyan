package app.manganyan.data.repository

import android.util.Log
import app.manganyan.common.Resource
import app.manganyan.domain.model.Comment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor() : CommentRepository {
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override suspend fun getComment(): List<Comment> {
        try {
            val querySnapshot = firebaseFirestore.collection("comments").get().await()
            val commentsList = mutableListOf<Comment>()
            querySnapshot.documents.map { document ->
                commentsList.add(
                    Comment(
                        id = null,
                        userId = document.getString("userId") ?: "",
                        chapterId = document.getString("chapterId") ?: "",
                        content = document.getString("content") ?: "",
                    )
                )
            }

            return commentsList
        } catch (e: Exception) {
            return emptyList()
        }
    }

    override fun postComment(comment: Comment): Flow<Resource<Unit>> {

        return flow {
            emit(Resource.Loading())

            try {

                val commentMap = mapOf(
                    "userId" to comment.userId,
                    "chapterId" to comment.chapterId,
                    "content" to comment.content
                )

                firebaseFirestore.collection("comments")
                    .add(commentMap)
                    .await()

                emit(Resource.Success(Unit))
            } catch (e: Exception) {
                emit(Resource.Error(message = e.message.toString()))
            }
        }
    }
}