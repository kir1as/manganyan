package app.manganyan.domain.interactor

import app.manganyan.common.Resource
import app.manganyan.data.dto.toMangaData
import app.manganyan.domain.model.MangaData
import app.manganyan.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MangaGetFilterUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(filter: String = "") : Flow<Resource<List<MangaData>>> = flow {
        try {
            emit(Resource.Loading<List<MangaData>>())
            val mangaData = repository.getMangaListByTitle(filter).map {
                it?.toMangaData()
            }

            emit(Resource.Success<List<MangaData>>(mangaData as List<MangaData>))

        }
        catch (e: HttpException){
            emit(Resource.Error<List<MangaData>>(message = e.localizedMessage?:"An Exception Occurred"))
        }
        catch (e: IOException){
            emit(Resource.Error<List<MangaData>>(message = "No Internet connexion"))
        }
    }
}