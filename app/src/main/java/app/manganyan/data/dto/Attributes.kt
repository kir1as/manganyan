package app.manganyan.data.dto


import app.manganyan.domain.model.MangaData
import com.squareup.moshi.Json
import org.w3c.dom.Attr

data class Attributes(
    @Json(name = "altTitles")
    val altTitles: List<AltTitle>,
    @Json(name = "availableTranslatedLanguages")
    val availableTranslatedLanguages: List<String>,
    @Json(name = "chapterNumbersResetOnNewVolume")
    val chapterNumbersResetOnNewVolume: Boolean,
    @Json(name = "contentRating")
    val contentRating: String,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "description")
    val description: Description,
    @Json(name = "isLocked")
    val isLocked: Boolean,
    @Json(name = "lastChapter")
    val lastChapter: String,
    @Json(name = "lastVolume")
    val lastVolume: String,
    @Json(name = "latestUploadedChapter")
    val latestUploadedChapter: String,
    @Json(name = "links")
    val links: Links,
    @Json(name = "originalLanguage")
    val originalLanguage: String,
    @Json(name = "publicationDemographic")
    val publicationDemographic: String,
    @Json(name = "state")
    val state: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "tags")
    val tags: List<Tag>,
    @Json(name = "title")
    val title: Title,
    @Json(name = "updatedAt")
    val updatedAt: String,
    @Json(name = "version")
    val version: Int,
    @Json(name = "year")
    val year: Int
)

