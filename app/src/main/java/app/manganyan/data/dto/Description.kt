package app.manganyan.data.dto


import com.squareup.moshi.Json

data class Description(
    @Json(name = "en")
    val en: String,
    @Json(name = "ja")
    val ja: String
)