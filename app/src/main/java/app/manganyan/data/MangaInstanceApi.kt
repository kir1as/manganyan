package app.manganyan.data

import app.manganyan.data.dto.MangaResponse
import app.manganyan.data.dto.chapter.ChapterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MangaInstanceApi {
    @GET("manga?limit=100&includes[]=author&includes[]=artist&includes[]=cover_art")
    suspend fun getMangaList() : MangaResponse

    @GET("at-home/server/{id}")
    suspend fun getMangaPage(@Path("id") id: String ): ChapterResponse
}