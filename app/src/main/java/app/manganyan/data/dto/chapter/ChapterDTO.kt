package app.manganyan.data.dto.chapter


import app.manganyan.domain.model.Chapter
import com.squareup.moshi.Json

data class ChapterDTO(
    @Json(name = "data")
    val `data`: List<String>,
    @Json(name = "dataSaver")
    val dataSaver: List<String>,
    @Json(name = "hash")
    val hash: String
)


fun ChapterDTO.toChapter() : Chapter = Chapter(
    hash = hash ?: "",
    data = data ?: emptyList(),
    dataSaver = dataSaver ?: emptyList()
)