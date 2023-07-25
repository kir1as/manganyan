package app.manganyan.data

import app.manganyan.data.dto.MangaResponse
import retrofit2.http.GET

interface MangaInstanceApi {

    @GET("manga?includes[]=author&includes[]=artist&includes[]=cover_art")
    suspend fun getMangaList() : MangaResponse
}