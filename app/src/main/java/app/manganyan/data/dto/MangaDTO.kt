package app.manganyan.data.dto


import app.manganyan.domain.model.MangaData
import app.manganyan.domain.model.MangaDetailData
import com.squareup.moshi.Json

data class MangaDTO(
    @Json(name = "attributes")
    val attributes: Attributes?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "relationships")
    val relationships: List<Relationship?>,
    @Json(name = "type")
    val type: String?
)

fun MangaDTO.toMangaData(): MangaData {
    val title = attributes?.title?.en ?: "Unknown"
    val coverId = relationships.getOrNull(2)?.id ?: "Unknown"
    val description = attributes?.description?.en ?: "Unknown"
    val image = relationships.getOrNull(2)?.attributes?.fileName
    val author = relationships[0]?.attributes?.name

    return MangaData(
        id = id,
        title = title,
        cover_id = coverId,
        description = description,
        image = image,
        author = author
    )
}

fun MangaDTO.toMangaDetailData(): MangaDetailData {
    val title = attributes?.title?.en ?: "Unknown"
    val coverId = relationships.getOrNull(2)?.id ?: "Unknown"
    val description = attributes?.description?.en ?: "Unknown"
    val image = relationships.getOrNull(2)?.attributes?.fileName
    val author = relationships[0]?.attributes?.name

    return MangaDetailData(
        id = id,
        title = title,
        cover_id = coverId,
        description = description,
        image = image,
        author = author
    )
}