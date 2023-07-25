package app.manganyan.data.dto


import com.squareup.moshi.Json

data class Relationship(
    @Json(name = "attributes")
    val attributes: AttributesXX?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "type")
    val type: String?
)