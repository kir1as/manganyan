package app.manganyan.data.dto


import com.squareup.moshi.Json

data class Links(
    @Json(name = "al")
    val al: String?,
    @Json(name = "kt")
    val kt: String?,
    @Json(name = "mu")
    val mu: String?,
    @Json(name = "raw")
    val raw: String?
)