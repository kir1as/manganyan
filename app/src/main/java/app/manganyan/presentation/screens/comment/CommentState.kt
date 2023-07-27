package app.manganyan.presentation.screens.comment

import app.manganyan.domain.model.Comment

data class CommentState (
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val comments: List<Comment> = emptyList(),
    val isError: String? = ""
)



