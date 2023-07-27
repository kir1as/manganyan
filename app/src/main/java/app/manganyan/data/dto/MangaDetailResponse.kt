package app.manganyan.data.dto

import com.squareup.moshi.Json

data class MangaDetailResponse (
    @Json(name="data")
    val data: MangaDTO
)