package app.manganyan.domain.interactor

import app.manganyan.common.Resource
import app.manganyan.domain.model.Chapter
import app.manganyan.domain.repository.ChapterRepository
import app.manganyan.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class getMangaPageUC @Inject constructor(
    private val repository: ChapterRepository
){
    operator fun invoke(id: String) : Flow<Resource<Chapter?>> = flow {
        try {
            emit(Resource.Loading())
            val mangaPage = repository.getMangaPage(id)
            emit(Resource.Success(mangaPage))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Error with ${e.localizedMessage}"))
        }
    }
}