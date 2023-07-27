package app.manganyan.data.dto

import com.squareup.moshi.Json

data class ChaptersDTO(
    @Json(name = "attributes")
    val attributes: AttributesChapter?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "relationships")
    val relationships: List<Relationship?>,
    @Json(name = "type")
    val type: String?
)