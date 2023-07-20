package app.manganyan.data.dto


import com.squareup.moshi.Json

data class Name(
    @Json(name = "en")
    val en: String
)