package app.manganyan.domain.interactor

import app.manganyan.common.Resource
import app.manganyan.data.dto.toMangaDetailData
import app.manganyan.domain.model.MangaDetailData
import app.manganyan.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MangaGetByIdUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    operator fun invoke(mangaId: String) : Flow<Resource<MangaDetailData>> = flow {
        try {
            emit(Resource.Loading())
            val manga = repository.getMangaById(mangaId = mangaId)?.toMangaDetailData()


            emit(Resource.Success(manga))

        }
        catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage?:"An Exception Occurred"))
        }
        catch (e: IOException){
            emit(Resource.Error(message = "No Internet connexion"))


        }
    }
}