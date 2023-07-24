package app.manganyan.data

import app.manganyan.data.dto.MangaResponse
import retrofit2.http.GET

interface MangaInstanceApi {

    @GET("manga")
    suspend fun getMangaList() : MangaResponse
}