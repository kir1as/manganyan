package app.manganyan.data.dto


import com.squareup.moshi.Json

data class AttributesX(
    @Json(name = "description")
    val description: DescriptionX,
    @Json(name = "group")
    val group: String,
    @Json(name = "name")
    val name: Name,
    @Json(name = "version")
    val version: Int
)