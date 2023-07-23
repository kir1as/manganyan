package app.manganyan.domain.model

data class Comment(
	val id: String? = null,
	val userId: String,
	val chapterId: String,
	val content: String,
)


