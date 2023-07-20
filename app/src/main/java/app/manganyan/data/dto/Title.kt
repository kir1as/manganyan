package app.manganyan.data.dto


import app.manganyan.domain.model.MangaData
import com.squareup.moshi.Json

data class Title(
    @Json(name = "en")
    val en: String
)

fun Title.toMangaData(): MangaData {
    return MangaData(
        id = "",
        title = "",
        cover_id= "",
        description= ""
    )
}