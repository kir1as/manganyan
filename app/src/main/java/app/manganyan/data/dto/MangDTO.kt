package app.manganyan.data.dto


import com.squareup.moshi.Json

data class MangDTO(
    @Json(name = "attributes")
    val attributes: Attributes,
    @Json(name = "id")
    val id: String,
    @Json(name = "relationships")
    val relationships: List<Relationship>,
    @Json(name = "type")
    val type: String
)