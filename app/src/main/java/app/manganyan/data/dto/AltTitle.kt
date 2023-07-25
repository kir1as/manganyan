package app.manganyan.data.dto


import com.squareup.moshi.Json

data class AltTitle(
    @Json(name = "ja")
    val ja: String?,
    @Json(name = "ja-ro")
    val jaRo: String?,
    @Json(name = "ne")
    val ne: String?
)