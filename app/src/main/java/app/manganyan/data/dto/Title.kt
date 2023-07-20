package app.manganyan.data.dto


import com.squareup.moshi.Json

data class Title(
    @Json(name = "en")
    val en: String
)