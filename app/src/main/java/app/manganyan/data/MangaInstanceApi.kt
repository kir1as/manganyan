package app.manganyan.data

import app.manganyan.data.dto.MangaResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MangaInstanceApi {
    @GET("manga?limit=100&includes[]=author&includes[]=artist&includes[]=cover_art")
    suspend fun getMangaList() : MangaResponse

    @GET("manga?limit=100&includes[]=author&includes[]=artist&includes[]=cover_art")
    suspend fun getMangaListByTitle(@Query("title") title: String = "") : MangaResponse
}