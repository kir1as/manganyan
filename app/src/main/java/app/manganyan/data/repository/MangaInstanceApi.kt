package app.manganyan.data.repository

import app.manganyan.data.dto.MangDTO
import retrofit2.http.GET

interface MangaInstanceApi {

    @GET("manga")
    suspend fun getMangaList() : MangDTO
}