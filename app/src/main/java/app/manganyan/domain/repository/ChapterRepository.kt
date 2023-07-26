package app.manganyan.domain.repository

import app.manganyan.data.dto.chapter.ChapterResponse
import app.manganyan.domain.model.Chapter

interface ChapterRepository {

    suspend fun getMangaPage(id: String): Chapter

}