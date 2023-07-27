package app.manganyan.domain.interactor

import javax.inject.Inject

data class CommentInteractor @Inject constructor(
    val getCommentsUC: GetCommentsUC,
)
