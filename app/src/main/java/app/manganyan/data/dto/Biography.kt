package app.manganyan.data.dto


import com.squareup.moshi.Json

data class Biography(
    @Json(name = "en")
    val en: String?
)