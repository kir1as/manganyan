package app.manganyan.data.repository

import app.manganyan.data.MangaInstanceApi
import app.manganyan.data.dto.MangaDTO
import app.manganyan.domain.repository.MangaRepository
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val api : MangaInstanceApi
): MangaRepository {
    override suspend fun getMangaList(): List<MangaDTO> {
        return api.getMangaList().data ?: emptyList()
    }
}