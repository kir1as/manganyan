package app.manganyan.data.dto


import com.squareup.moshi.Json

data class AltTitle(
    @Json(name = "en")
    val en: String,
    @Json(name = "ja")
    val ja: String,
    @Json(name = "ja-ro")
    val jaRo: String
)