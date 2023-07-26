package app.manganyan.domain.interactor

import android.util.Log
import app.manganyan.common.Resource
import app.manganyan.domain.model.Chapter
import app.manganyan.domain.repository.ChapterRepository
import app.manganyan.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.math.log

class getMangaPageUC @Inject constructor(
    private val repository: ChapterRepository
){
    operator fun invoke(id: String) : Flow<Resource<Chapter?>> = flow {
        try {
            emit(Resource.Loading())
            val mangaPage = repository.getMangaPage(id)
            Log.d("getMangaPageUC", "invoke: $mangaPage")
            emit(Resource.Success(mangaPage))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Error with ${e.localizedMessage}"))
        }
    }
}