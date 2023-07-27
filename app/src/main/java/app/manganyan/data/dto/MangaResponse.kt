package app.manganyan.data.dto

import com.squareup.moshi.Json

data class MangaResponse(
    @Json(name="data")
    val data: List<MangaDTO?>?
)
