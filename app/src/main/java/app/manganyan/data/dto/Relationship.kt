package app.manganyan.data.dto


import com.squareup.moshi.Json

data class Relationship(
    @Json(name = "id")
    val id: String,
    @Json(name = "related")
    val related: String,
    @Json(name = "type")
    val type: String
)