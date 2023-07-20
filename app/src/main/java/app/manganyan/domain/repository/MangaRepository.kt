package app.manganyan.domain.repository

import app.manganyan.data.dto.MangaDTO

interface MangaRepository {

    suspend fun getMangaList(): List<MangaDTO>
}