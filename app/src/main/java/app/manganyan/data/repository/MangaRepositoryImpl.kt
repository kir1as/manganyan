package app.manganyan.data.repository

import app.manganyan.data.MangaInstanceApi
import app.manganyan.data.dto.ChaptersDTO
import app.manganyan.data.dto.MangaDTO
import app.manganyan.domain.repository.MangaRepository
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val api : MangaInstanceApi
): MangaRepository {
    override suspend fun getMangaList(): List<MangaDTO?> {
        return api.getMangaList().data ?: emptyList()
    }

    override suspend fun getMangaListByTitle(filter: String): List<MangaDTO?> {
        return api.getMangaListByTitle(filter).data ?: emptyList()
    }

    override suspend fun getMangaById(mangaId: String): MangaDTO? {
        return api.getMangaById(mangaId).data ?: null
    }

    override suspend fun getChaptersId(mangaId: String): List<ChaptersDTO?> {
        return api.getChaptersId(mangaId).data ?: emptyList()
    }

}