package app.manganyan.data.repository

import app.manganyan.data.MangaInstanceApi
import app.manganyan.data.dto.chapter.ChapterDTO
import app.manganyan.data.dto.chapter.ChapterResponse
import app.manganyan.data.dto.chapter.toChapter
import app.manganyan.domain.model.Chapter
import app.manganyan.domain.repository.ChapterRepository
import app.manganyan.domain.repository.MangaRepository
import javax.inject.Inject

class MangaDataSource  @Inject constructor(private val mangaInstanceApi: MangaInstanceApi) :
    ChapterRepository {
    override suspend fun getMangaPage(id: String) : Chapter =
        mangaInstanceApi.getMangaPage(id).chapter.toChapter()


}