package app.manganyan.data.dto

import com.squareup.moshi.Json

data class ChaptersResponse(
    @Json(name="data")
    val data: List<ChaptersDTO?>?
)