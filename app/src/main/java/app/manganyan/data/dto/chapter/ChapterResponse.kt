package app.manganyan.data.dto.chapter


import app.manganyan.domain.model.Chapter
import com.squareup.moshi.Json

data class ChapterResponse(
    @Json(name = "baseUrl")
    val baseUrl: String,
    @Json(name = "chapter")
    val chapter: ChapterDTO,
    @Json(name = "result")
    val result: String
)
