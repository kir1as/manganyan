package app.manganyan.data.dto


import app.manganyan.domain.model.MangaData
import com.squareup.moshi.Json

data class MangaDTO(
    @Json(name = "attributes")
    val attributes: Attributes,
    @Json(name = "id")
    val id: String,
    @Json(name = "relationships")
    val relationships: List<Relationship>,
    @Json(name = "type")
    val type: String
)

fun MangaDTO.toMangaData(): MangaData {
    return MangaData(
        id = id,
        title = attributes.title.en,
        cover_id = relationships[2].id,
        description = attributes.title.en
    )
}