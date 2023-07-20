package app.manganyan.data.dto


import com.squareup.moshi.Json

data class Links(
    @Json(name = "al")
    val al: String,
    @Json(name = "amz")
    val amz: String,
    @Json(name = "bw")
    val bw: String,
    @Json(name = "ebj")
    val ebj: String,
    @Json(name = "mal")
    val mal: String
)