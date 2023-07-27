package app.manganyan.domain.repository

import app.manganyan.data.dto.ChaptersDTO
import app.manganyan.data.dto.MangaDTO
import app.manganyan.domain.model.Chapter

interface MangaRepository {
    suspend fun getMangaList(): List<MangaDTO?>
    suspend fun getMangaListByTitle(filter: String = ""): List<MangaDTO?>
    suspend fun getMangaById(mangaId: String): MangaDTO?
    suspend fun getChaptersId(mangaId: String): List<ChaptersDTO?>
}