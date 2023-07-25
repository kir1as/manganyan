package app.manganyan.domain.repository

import app.manganyan.data.dto.MangaDTO
import app.manganyan.domain.model.Chapter

interface MangaRepository {

    suspend fun getMangaList(): List<MangaDTO>


}