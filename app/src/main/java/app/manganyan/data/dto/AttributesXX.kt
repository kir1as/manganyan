package app.manganyan.data.dto


import com.squareup.moshi.Json

data class AttributesXX(
    @Json(name = "biography")
    val biography: Biography?,
    @Json(name = "booth")
    val booth: String?,
    @Json(name = "createdAt")
    val createdAt: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "fanBox")
    val fanBox: String?,
    @Json(name = "fantia")
    val fantia: Any?,
    @Json(name = "fileName")
    val fileName: String?,
    @Json(name = "imageUrl")
    val imageUrl: Any?,
    @Json(name = "locale")
    val locale: String?,
    @Json(name = "melonBook")
    val melonBook: Any?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "naver")
    val naver: Any?,
    @Json(name = "nicoVideo")
    val nicoVideo: Any?,
    @Json(name = "pixiv")
    val pixiv: String?,
    @Json(name = "skeb")
    val skeb: Any?,
    @Json(name = "tumblr")
    val tumblr: Any?,
    @Json(name = "twitter")
    val twitter: String?,
    @Json(name = "updatedAt")
    val updatedAt: String?,
    @Json(name = "version")
    val version: Int?,
    @Json(name = "volume")
    val volume: Any?,
    @Json(name = "website")
    val website: Any?,
    @Json(name = "weibo")
    val weibo: Any?,
    @Json(name = "youtube")
    val youtube: Any?
)