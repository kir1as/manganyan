package app.manganyan.di

import app.manganyan.common.Constants
import app.manganyan.data.MangaInstanceApi
import app.manganyan.data.repository.MangaRepositoryImpl
import app.manganyan.domain.repository.MangaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MangaModule {

    @Provides
    @Singleton
    fun provideApi (): MangaInstanceApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MangaInstanceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMangaRepository(api: MangaInstanceApi):MangaRepository{
        return MangaRepositoryImpl(api)
    }
}