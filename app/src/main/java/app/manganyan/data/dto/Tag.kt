package app.manganyan.data.dto


import com.squareup.moshi.Json

data class Tag(
    @Json(name = "attributes")
    val attributes: AttributesX,
    @Json(name = "id")
    val id: String,
    @Json(name = "relationships")
    val relationships: List<Any>,
    @Json(name = "type")
    val type: String
)