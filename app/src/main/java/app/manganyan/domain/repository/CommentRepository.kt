package app.manganyan.domain.repository

import app.manganyan.common.Resource
import app.manganyan.domain.model.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    suspend fun getComment(): List<Comment>
    fun postComment(comment: Comment): Flow<Resource<Unit>>

}